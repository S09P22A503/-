from flask import Blueprint,request,jsonify
from app.database.queries import increase_data
from sparksession import CommonSparkSession
from recommendation_model import RecommendationModel
bp = Blueprint( 'main' , __name__ , url_prefix='/' )

@bp.route('/',methods=['POST','GET'])
def hello():
  return "sdasd";

@bp.route('/data',methods=['POST'])
def collect_data():
  data = request.get_json()
  
  user_id = data.get('user_id',None)
  article_id = data.get('article_id',None)
  dtype = data.get('dtype',None)
  viewingtime = data.get('viewingtime',None)

  # 중복되는 부분 validator 찾아보기
  if not user_id :
    return jsonify({"message": "user_id not found"}),400
  
  if not article_id :
    return jsonify({"message": "article_id is not found"}),400
  
  #dtype에 다 해당안될경우?
  if not dtype :
    return jsonify({"message": "dtype is not found"}),400
  
  increase_data(user_id = user_id,article_id = article_id,dtype = dtype,viewingtime = viewingtime)
  
  return jsonify({"message" : "update data completly"}),200
  
@bp.route('/recommend/user',methods=['GET'])
def recommend_item_by_userId():
  userId = request.args.get('userId')

  print(type(userId))
  # 추천리스트 받기
  recommendList = RecommendationModel().get_recommendation_for_user(int(userId))

  return jsonify(recommendList)