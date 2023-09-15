from pyspark import SparkConf
from pyspark.sql import SparkSession

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
      # PySpark 세션 초기화
      conf = SparkConf()
      conf.set("spark.hadoop.fs.s3a.access.key","AKIAVBDPFAQOKFRIMRGE")
      conf.set("spark.hadoop.fs.s3a.secret.key","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")
      conf.set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
      conf.set("spark.driver.extraClassPath",r"C:\Users\SSAFY\Documents\spark_prac\driver\postgresql-42.6.0.jar")
      conf.set('spark.hadoop.fs.s3a.aws.credentials.provider', 'org.apache.hadoop.fs.s3a.SimpleAWSCredentialsProvider')
      
      self.__spark = SparkSession.builder \
          .appName("LoadModelFromS3") \
          .config(conf=conf) \
          .getOrCreate()
