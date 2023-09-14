from pyspark.sql import SparkSession
from pyspark.ml.recommendation import ALS
from pyspark.sql.types import FloatType
from pyspark.sql.functions import udf,col
from pyspark.ml.feature import StringIndexer
import redis
import pickle
# import pika

#implicit rating 계산
def calculate_implicit_rating(viewcount,buycount,viewingtime):
  viewcount_weight = 1
  buycount_weight = 5
  viewingtime_weight = 0.5
  return viewcount * viewcount_weight \
    + buycount * buycount_weight \
    + viewingtime * viewingtime_weight 


# PySpark 초기화
spark = SparkSession.builder. \
appName("Implicit ALS Recommender"). \
config("spark.driver.extraClassPath",r"C:\Users\SSAFY\Documents\spark_prac\driver\postgresql-42.6.0.jar"). \
master("local[*]"). \
getOrCreate()

r = redis.Redis(host='localhost',port = 6379, db = 0)

# 예시: PostgreSQL에서 데이터 로드 (사용자 ID, 항목 ID, 신뢰도)c
data = spark.read.jdbc("jdbc:postgresql://localhost:5432/mldataset", "implicit", properties={"user": "postgres", "password": "ssafy","driver":"org.postgresql.Driver"})

# ALS는 숫자 ID를 요구하기 때문에 StringIndexer를 사용하여 문자열 ID를 숫자로 변환
# userIndexer = StringIndexer(inputCol="userIdStr", outputCol="userId").fit(data)
# itemIndexer = StringIndexer(inputCol="itemIdStr", outputCol="itemId").fit(data)

# data = userIndexer.transform(data)
# data = itemIndexer.transform(data)

#UDF 변환
calculate_rating_udf = udf(calculate_implicit_rating,FloatType())

data = data.withColumn("score",calculate_rating_udf(col("viewcount"),col("buycount"),col("viewingtime")))

# Implicit ALS 모델 학습
als = ALS(maxIter=5, regParam=0.01, implicitPrefs=True, userCol="user_id", itemCol="article_id", ratingCol="score")
model = als.fit(data)

# 모델 저장
model_path = r"C:\Users\SSAFY\Documents\spark_prac\model"
model.write().overwrite().save(model_path)

# 모델 직렬화 및 redis에 저장
serialized_model = pickle.dumps(model)

# Redis에 저장
r = redis.Redis(host='localhost', port=6379, db=0)
model_key = "recommend_model"
r.set(model_key, serialized_model)


# redis pub/sub 채널에 모델 학습 사실을 publish
r.publish('recommend-model-train','model train completed')


spark.stop()
# RabbitMQ 연결 및 메시지 전송
# connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
# channel = connection.channel()

# channel.queue_declare(queue='model_updates')

# # 학습 완료 메시지 전송
# message = {"message": "model_updated", "model_path": model_path}
# channel.basic_publish(exchange='', routing_key='model_updates', body=str(message))

# connection.close()
