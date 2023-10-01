import scrapy
from datetime import date,timedelta
from ..items import AgromarketItem

class AgromarketSpider(scrapy.Spider):
    name = "agromarket"
    start_urls = ["https://at.agromarket.kr/domeinfo/sanRealtime.do"]


    def start_requests(self):
        start_date = date(2019, 1, 1)
        self.end_date = date(2023,10,1)
        self.day_delta = timedelta(days=1)
        self.page_size = 100
        self.current_date = start_date
        self.page_no = 1
        yield self.generate_request()
        
    def crawl_next_day(self):
        self.current_date+=self.day_delta
               
    
    def init_page(self):
        self.page_no = 1           
    
    def crawl_next_page(self):
        self.page_no+=1
            
    def generate_request(self):
        url = f"{self.start_urls[0]}?pageNo={self.page_no}&saledateBefore={self.current_date}&largeCdBefore=&midCdBefore=&smallCdBefore=&saledate={self.current_date}&whsalCd=&cmpCd=&sanCd=&smallCdSearch=&largeCd=&midCd=&smallCd=&pageSize={self.page_size}"
        return scrapy.Request(url=url, callback=self.parse)
    
    def parse(self, response):
        
        if(self.current_date > self.end_date):
            return
        
        table = response.css('.table_type_sub.small')
        rows = table.css('tbody tr')
        
        # row가 없는 경우 바로 종료
        
        # 첫행 두번째 열 추출
        market = rows[0].css('td:nth-child(2)::text').get()
        
        if not market:
            self.crawl_next_day()
            self.init_page()
            yield self.generate_request()
        else:
            for row in rows:
                item = AgromarketItem(   
                    date_time=row.css('td:nth-child(1)::text').get(),
                    market=row.css('td:nth-child(2)::text').get(),
                    corporation=row.css('td:nth-child(3)::text').get(),
                    category=row.css('td:nth-child(4)::text').get(),
                    product=row.css('td:nth-child(5)::text').get(),
                    variety=row.css('td:nth-child(6)::text').get(),
                    origin=row.css('td:nth-child(7)::text').get(),
                    specification=row.css('td:nth-child(8)::text').get(),
                    volume=row.css('td:nth-child(9)::text').get(),
                    price=row.css('td:nth-child(10)::text').get()
                )
                if(item["date_time"] is None):
                    item["date_time"] = self.current_date.strftime("%Y-%m-%d")
                else:
                    item["date_time"] = item["date_time"].split()[0]
                yield item
                
            self.crawl_next_page()
            yield self.generate_request()