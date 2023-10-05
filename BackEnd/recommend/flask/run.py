from flask import  Flask
import redis
import os
import threading
from pyspark.ml.recommendation import ALSModel
from sparksession import CommonSparkSession

from recommendation_model import RecommendationModel
is_started = False

def get_recommendation_model():

  s3_path = "s3a://a503/model/"

  model = ALSModel.load(s3_path)

  print("추천 시스템 모델을 불러오는중입니다.")

  if model is None:
    raise ValueError("S3에 모델이 존재하지 않습니다")


  return model


def replace_train_model():
  model = get_recommendation_model()

  if model is None:
    raise ValueError("Redis에 학습된 모델이 없습니다.")
  
  RecommendationModel().set_recommendation_model(model)
  print(model.userFactors.count())
  print(model.itemFactors.count())
  print("Debug: 추천시스템 모델 교체 완료")


def listen_train_model():

  redisHost = os.environ.get('RECOMMEND_REDIS_HOST')

  if redisHost is None:
    redisHost = 'localhost'
  
  redisPassword = os.environ.get('RECOMMEND_REDIS_PASSWORD')



  r = redis.Redis(host=redisHost,port = 6379, db = 0,password = redisPassword)

  p = r.pubsub()
  p.subscribe('recommend-model-train')

  for msg in p.listen():
    if msg['type'] == 'message':
      print("Debug : subscribe 수신 완료")
      replace_train_model()
      print("replace train model")


def create_app():

  global is_started

  app = Flask(__name__)

  app.config.from_object('config')

  from app.views import views
  from app.database import connection


  # DB연결
  connection.setup_db(app)

  CommonSparkSession().init_spark_session()

  #Blueprint 등록 (라우팅)
  app.register_blueprint(views.bp)

  #Redis 서버에 존재하는 모델을 가져옴
  replace_train_model()

  #Redis Subscribe Thread 실행
  if(not is_started):
    threading.Thread(target=listen_train_model, daemon=True).start()
    is_started = True
    
  return app