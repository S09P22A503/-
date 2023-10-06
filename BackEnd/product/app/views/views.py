from flask import Blueprint,request,jsonify
from app.database.queries import retrieve_price_data,retrieve_itemList,retrieve_productList
import json

bp = Blueprint( 'main' , __name__ , url_prefix='/products' )

@bp.route('/',methods=['GET'])
def retrieve_product():
  if("itemId" not in request.args):
    return jsonify(retrieve_itemList()),200
  else:
    itemId = request.args.get("itemId")
    
    if (not isinstance(itemId, str ) or len(itemId)!=3):
      return jsonify(error="Invalid itemId"), 400
    
    return jsonify(retrieve_productList(itemId=itemId)),200


@bp.route('/history/retail',methods=['GET'])
def retrieve_retail_product_price_history():
  
  nameId = request.args.get('nameId')
  
  itemId = request.args.get('itemId')
  
  
  if(nameId is None):
    return jsonify({"message": "nameId not found"}),400
  
  if(itemId is None):
    return jsonify({"message": "itemId not found"}),400
  
  resultRow = retrieve_price_data(itemId=itemId,nameId=nameId,is_retail=True)
  
  return jsonify(resultRow),200
    

@bp.route('/history/wholesale',methods=['GET'])
def retrieve_wholesale_product_price_history():
  
  nameId = request.args.get('nameId')
  
  itemId = request.args.get('itemId')
  
  if(nameId is None):
    return jsonify({"message": "nameId not found"}),400
  
  if(itemId is None):
    return jsonify({"message": "itemId not found"}),400
  
  resultRow = retrieve_price_data(itemId=itemId,nameId=nameId,is_retail=False)
  
  return jsonify(resultRow),200

