from pyspark.sql import SparkSession
from pyspark.ml.recommendation import ALS
from pyspark.sql.types import FloatType
from pyspark.sql.functions import udf,col
from pyspark.sql import Row
from pyspark.sql.functions import lit
from pyspark import SparkConf
import redis
from apscheduler.schedulers.blocking import BlockingScheduler
import os
import requests
from sqlalchemy import create_engine, MetaData, Table
sched = BlockingScheduler()

# import pika

#implicit rating 계산
def calculate_implicit_rating(viewcount,buycount,viewingtime):
  viewcount_weight = 1
  buycount_weight = 5
  viewingtime_weight = 0.5
  return viewcount * viewcount_weight \
    + buycount * buycount_weight \
    + viewingtime * viewingtime_weight 

# Spark 설정
conf = SparkConf()

# 배포 환경
# Spark Driver Path
classPath = os.environ.get("SPARK_DRIVER_CLASSPATH")

# S3 Accesskey
S3AccessKey = os.environ.get("S3_ACCESS_KEY")

# S3 SevrerKey
S3SecretKey = os.environ.get("S3_SECRET_KEY")

# S3 버킷 ID
S3BucketId = os.environ.get("S3_BUCKET_ID")

# 데이터셋을 가져올 PostgreSQL DB Host 설정
PostgreSQLHost = os.environ.get("RECOMMEND_DB_HOST")

# 데이터셋을 가져올 PostgreSQL DB Password 설정
PostgreSQLPassword = os.environ.get("RECOMMEND_DB_PASSWORD")

# PostgreSQL DB jdbc 버전
PostgreSQLVersion = os.environ.get("POSTGRESQL_VERSION")

ItemDbHost = os.environ.get("ARTICLE_DB_HOST")

ItemDbPassword = os.environ.get("ARTICLE_DB_PASSWORD")



print(PostgreSQLHost+" "+PostgreSQLPassword)

if PostgreSQLHost is None:
   PostgreSQLHost = "localhost"

# Spark 설정
conf.set("spark.hadoop.fs.s3a.access.key",S3AccessKey)
conf.set("spark.hadoop.fs.s3a.secret.key",S3SecretKey)
conf.set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
conf.set("spark.driver.extraClassPath",classPath+"/postgresql-"+PostgreSQLVersion+".jar")
conf.set('spark.hadoop.fs.s3a.aws.credentials.provider', 'org.apache.hadoop.fs.s3a.SimpleAWSCredentialsProvider')



# PySpark Session 초기화 및 생성
spark = SparkSession.builder. \
appName("Implicit ALS Recommender"). \
config(conf = conf). \
master("local[*]"). \
getOrCreate()

# Redis 연결정보 가져오기

redisHost = os.environ.get('RECOMMEND_REDIS_HOST')
if redisHost is None:
   redisHost = 'localhost'
redisPassword = os.environ.get('RECOMMEND_REDIS_PASSWORD')

# Pgsql Driver 디버깅 코드
print(os.path.exists(os.environ.get("SPARK_DRIVER_CLASSPATH") + "/postgresql-42.6.0.jar"))

# Schedueling이 필요한 작업들

@sched.scheduled_job('interval',seconds = 2400, id = 'train_the_model')
def train_als():

    print("모델 학습 시작")
    # member 테이블의 id를 가져옴
    member_ids = requests.get(url="https://j9a503a.p.ssafy.io/members/all").json()["data"]

    # 두 번째 데이터베이스에서 article 테이블의 product_id를 가져옴
    metadata = MetaData()
    engine2 = create_engine("mysql+pymysql://sshmarket_article_user:"+ItemDbPassword+"@"+ItemDbHost+":3308/sshmarket_article")
    article = Table('Article', metadata, autoload_with=engine2)
    connection = engine2.connect()
    product_ids = connection.execute(article.select()).fetchall()
    product_ids = [row[0] for row in product_ids]

    data = spark.read.jdbc("jdbc:postgresql://"+PostgreSQLHost+":5432/mldataset", "implicit", properties={"user": "postgres", "password": PostgreSQLPassword,"driver":"org.postgresql.Driver"})

    # UDF 변환
    calculate_rating_udf = udf(calculate_implicit_rating, FloatType())
    data = data.withColumn("score", calculate_rating_udf(col("viewcount"), col("buycount"), col("viewingtime")))

    # 모든 사용자와 아이템의 조합 생성
    all_users = spark.createDataFrame([Row(user_id=uid) for uid in member_ids])
    all_items = spark.createDataFrame([Row(article_id=pid) for pid in product_ids])
    
    sampled_items = all_items.sample(False, 0.1) 
    
    # 기존의 data 데이터 프레임에 full_combinations 조인
    full_combinations = all_users.crossJoin(sampled_items)
    full_data = full_combinations.join(data, on=["user_id", "article_id"], how="left_outer").fillna({'score': 0}) # fillna를 사용하여 누락된 평가를 0으로 설정

    # Implicit ALS 모델 학습
    als = ALS(maxIter=5, regParam=0.01, implicitPrefs=True, userCol="user_id", itemCol="article_id", ratingCol="score")
    model = als.fit(full_data) # full_data 사용

    # 모델을 S3에 저장
    s3path = "s3a://"+S3BucketId+"/model"
    model.write().overwrite().save(s3path)

    # Redis에 저장

    r = redis.Redis(host=redisHost, port=6379, db=0,password=redisPassword)

    # model_key = "recommend_model"

    # r.set(model_key, model_bytes)


    # redis pub/sub 채널에 모델 학습 사실을 publish
    r.publish('recommend-model-train','model train completed')

    print("모델 학습 완료")

# 최초 실행
train_als()

sched.start()
