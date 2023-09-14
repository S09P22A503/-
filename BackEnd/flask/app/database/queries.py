from .connection import get_connection
from sqlalchemy import MetaData,Table,select,insert,update

metadata = MetaData()

#데이터셋 
_data = None

def get_data_table():
  global _data
  if _data is None:
    _data = Table('implicit',metadata, autoload_with=get_connection())
  return _data


def increase_data(user_id,article_id,dtype,viewingtime = None):
  data = get_data_table()
  connection = get_connection()
  try:
    select_query = (
      select(data)
      .where(data.c.user_id == user_id)
      .where(data.c.article_id == article_id)
      )
    result = connection.execute(select_query)
    row = result.fetchone()
    
    if(row is None):
      insert_values = {
        "user_id" : user_id,
        "article_id" : article_id,
        "viewcount" : 0,
        "buycount" : 0,
        "viewingtime" : 0,
        "bookmark" : False
      }
      
      if( dtype == "viewcount" ):
        insert_values["viewcount"] = 1
      elif( dtype == "buycount" ):
        insert_values["buycount"] = 1
      elif( dtype == "bookmark" ):
        insert_values["bookmark"] = True

      insert_query = (
        insert(data)
        .values(
          **insert_values
        )
      )
      print("insert !!!")
      connection.execute(insert_query)
      connection.commit()
      return True

    
    update_query = (
      update(data)
      .where(data.c.user_id == user_id)
      .where(data.c.article_id == article_id)
    )

    if(dtype == "viewcount"):
      update_query = (
        update_query
        .values(viewcount = data.c.viewcount+1)
      )
    elif(dtype == "buycount") :
      update_query = (
        update_query
        .values(buycount = data.c.buycount+1)
      )
    elif(dtype == "viewingtime") :
      if(viewingtime is None):
        raise ValueError("viewingtime이 존재햐아합니다.")
      update_query = (
        update_query
        .values(viewingtime = data.c.viewingtime + viewingtime)
      )
    connection.execute(update_query)
    connection.commit()
    return True
  finally:
    connection.close()
    