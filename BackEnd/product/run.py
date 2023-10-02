from flask import  Flask
from app.util.DateJsonEncoder import CustomJSONEncoder
from flask_cors import CORS

def create_app():

  app = Flask(__name__)

  CORS(app)

  app.config.from_object('config')

  from app.views import views
  from app.database import connection


  # DB연결
  connection.setup_db(app)
  
  app.register_blueprint(views.bp)
  
  # DateTime 변환
  app.json_encoder = CustomJSONEncoder
  
  # json 변환시 한글 유니코드화 문제
  app.config['JSON_AS_ASCII'] = False
  return app