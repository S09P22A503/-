from flask import  Flask
import redis
import threading

is_started = False

def listen_train_model():
  r = redis.Redis()
  p = r.pubsub()
  p.subscribe('recommend-model-train')

  print('is thread run twice?')

  for msg in p.listen():
    if msg['type'] == 'message':
      print(msg['data'].decode('utf-8'))


def create_app():

  global is_started

  app = Flask(__name__)

  app.config.from_object('config')

  from app.views import views
  from app.database import connection
  

  #DB연결
  connection.setup_db(app)

  #Blueprint 등록 (라우팅)

  #Redis Subscribe Thread 실행

  if(not is_started):
    threading.Thread(target=listen_train_model, daemon=True).start()
    is_started = True
  app.register_blueprint(views.bp)
  return app