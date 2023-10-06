from flask import  Flask
from flask_cors import CORS
def create_app():

  global is_started

  app = Flask(__name__)

  app.config.from_object('config')
  CORS(app)
  from app.views import views
  from app.database import connection


  # DB연결
  connection.setup_db(app)

  #Blueprint 등록 (라우팅)
  app.register_blueprint(views.bp)
  return app