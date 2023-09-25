from pyspark.sql import SparkSession
from pyspark.ml.recommendation import ALS
from pyspark.sql.types import FloatType
from pyspark.sql.functions import udf,col
from pyspark import SparkConf
import redis
from apscheduler.schedulers.blocking import BlockingScheduler
import os
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

if PostgreSQLHost is None:
   PostgreSQLHost = "localhost"

# 개발 환경
if(classPath is None):
    classPath = r"C:\Users\SSAFY\Documents\spark_prac\driver\postgresql-42.6.0.jar"  

# Spark 설정
conf.set("spark.hadoop.fs.s3a.access.key",S3AccessKey)
conf.set("spark.hadoop.fs.s3a.secret.key",S3SecretKey)
conf.set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
conf.set("spark.driver.extraClassPath",classPath)
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

@sched.scheduled_job('interval',seconds = 1200, id = 'train_the_model')
def train_als():

  print("모델 학습 시작")

  # 예시: PostgreSQL에서 데이터 로드 (사용자 ID, 항목 ID, 신뢰도)
  # data 는 Spark DataFrame으로 지연연산을 하기때문에 데이터 처리에 관한 액션이 일어나지 않을경우 
  # SQL 쿼리를 보내지 않아 메모리에 데이터가 로드 되지 않음
  # show() count() collect() 시에 SQL 쿼리 발생
  data = spark.read.jdbc("jdbc:postgresql:/"+PostgreSQLHost+":5432/mldataset", "implicit", properties={"user": "postgres", "password": PostgreSQLPassword,"driver":"org.postgresql.Driver"})
  #UDF 변환
  calculate_rating_udf = udf(calculate_implicit_rating,FloatType())

  data = data.withColumn("score",calculate_rating_udf(col("viewcount"),col("buycount"),col("viewingtime")))

  # Implicit ALS 모델 학습
  als = ALS(maxIter=5, regParam=0.01, implicitPrefs=True, userCol="user_id", itemCol="article_id", ratingCol="score")
  model = als.fit(data)

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
