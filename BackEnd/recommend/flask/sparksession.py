from pyspark import SparkConf
from pyspark.sql import SparkSession
import os
class CommonSparkSession:
  __spark = None
  __instance = None

  def __new__(cls, *args, **kwargs):
      if not cls.__instance:
          cls.__instance = super(CommonSparkSession, cls).__new__(cls, *args, **kwargs)
      return cls.__instance
  
  def get_spark_session(self):
    return self.__spark
  
  def init_spark_session(self):

      # S3 Accesskey
      S3AccessKey = os.environ.get("S3_ACCESS_KEY")

      # S3 SevrerKey
      S3SecretKey = os.environ.get("S3_SECRET_KEY")

      # PySpark 세션 초기화
      conf = SparkConf()
      conf.set("spark.hadoop.fs.s3a.access.key",S3AccessKey)
      conf.set("spark.hadoop.fs.s3a.secret.key",S3SecretKey)
      conf.set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
      conf.set('spark.hadoop.fs.s3a.aws.credentials.provider', 'org.apache.hadoop.fs.s3a.SimpleAWSCredentialsProvider')
      
      self.__spark = SparkSession.builder \
          .appName("LoadModelFromS3") \
          .config(conf=conf) \
          .getOrCreate()
