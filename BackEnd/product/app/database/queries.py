from .connection import get_connection
from sqlalchemy import MetaData,Table,select,distinct

metadata = MetaData()

#데이터셋 
_retail_table = None
_wholesale_table = None
_product_table = None

def get_retail_table():
  global _retail_table
  if _retail_table is None:
    _retail_table = Table('RetailPriceHistory',metadata, autoload_with=get_connection())
  return _retail_table


def get_wholesale_table():
  global _wholesale_table
  if _wholesale_table is None:
    _wholesale_table = Table('WholesalePriceHistory',metadata, autoload_with=get_connection())
  return _wholesale_table

def get_product_table():
  global _product_table
  if _product_table is None:
    _product_table = Table('SearchProduct',metadata, autoload_with=get_connection())
  return _product_table
  

def retrieve_price_data(nameId,itemId,is_retail=True):
  if(is_retail):
    data = get_retail_table()
  else:
    data = get_wholesale_table()
    
  connection = get_connection()
  try:
    select_query = (
      select(data)
      .where(data.c.nameId == nameId)
      .where(data.c.itemId == itemId)
      )
    result = connection.execute(select_query)
    rows = result.fetchall()
    connection.execute(select_query)
    connection.commit()
    results = [row._asdict() for row in rows ]
    return results
  finally:
    connection.close()
    
def retrieve_itemList():
  data = get_product_table()
    
  connection = get_connection()
  try:
    select_query = (
        select(data.c.itemName, data.c.itemId).distinct()
    )
    result = connection.execute(select_query)
    rows = result.fetchall()
    connection.execute(select_query)
    connection.commit()
    results = [row._asdict() for row in rows ]
    return results
  finally:
    connection.close()    
    
def retrieve_itemList():
  data = get_product_table()
    
  connection = get_connection()
  try:
    select_query = (
        select(data.c.itemName, data.c.itemId).distinct()
    )
    result = connection.execute(select_query)
    rows = result.fetchall()
    connection.execute(select_query)
    connection.commit()
    results = [row._asdict() for row in rows ]
    return results
  finally:
    connection.close()    
    
def retrieve_productList(itemId):
  data = get_product_table()
    
  connection = get_connection()
  try:
    select_query = (
        select(data.c.name, data.c.nameId).distinct()
        .where(data.c.itemId == itemId)
    )
    result = connection.execute(select_query)
    rows = result.fetchall()
    connection.execute(select_query)
    connection.commit()
    results = [row._asdict() for row in rows ]
    return results
  finally:
    connection.close()