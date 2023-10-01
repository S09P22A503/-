import re
import psycopg2
from datetime import datetime

class AgroDataCleaningPipeline(object):
    def process_item(self, item, spider):
        item['price'] = int(item['price'].replace(',',''))
        item['origin'] = re.sub('[\n\t]','',item['origin'])
        
        # 단위를 그램으로 변환
        spec_match= re.search(r'(\d*\.?\d+)(kg|g)', item['specification'])
        
        weight, unit = spec_match.groups()
        
        if(unit=='kg') :
            grams = int(float(weight) * 1000)
        else:
            grams = int(float(weight))
            
        item['specification'] = grams
        print(item)
        
        return item

class PostgreSQLPipeline(object):
    def open_spider(self, spider):
        # 데이터베이스 연결 설정
        self.connection = psycopg2.connect(
            host="j9a503.p.ssafy.io",
            database="postgres",
            user="postgres",
            password="ssafya503postgres",
            port="5431"
        )
        self.cursor = self.connection.cursor()

    def close_spider(self, spider):
        self.cursor.close()
        self.connection.close()

    def process_item(self, item, spider):
        try:
            insert_query = """
                INSERT INTO agro_data(trade_at, market, corporation, category, product, variety, origin, unit_gram, volume, price)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
            """
            self.cursor.execute(insert_query, (
                item['date_time'],
                item['market'],
                item['corporation'],
                item['category'],
                item['product'],
                item['variety'],
                item['origin'],
                item['specification'],
                item['volume'],
                item['price']
            ))
            self.connection.commit()
            
        except Exception as e:
            
            spider.logger.error(f"Failed to insert item due to {e}")
            self.connection.rollback()
            
        return item
