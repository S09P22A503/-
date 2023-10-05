from flask import Blueprint,request,jsonify
from app.database.queries import increase_data
bp = Blueprint( 'main' , __name__ , url_prefix='/' )

@bp.route('/collect',methods=['POST','GET'])
def hello():
  return "sdasd";

@bp.route('/collect/data',methods=['POST'])
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
