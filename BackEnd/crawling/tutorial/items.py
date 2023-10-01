# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class AgromarketItem(scrapy.Item):
    date_time = scrapy.Field()
    market = scrapy.Field()
    corporation = scrapy.Field()
    category = scrapy.Field()
    product = scrapy.Field()
    variety = scrapy.Field()
    origin = scrapy.Field()
    specification = scrapy.Field()
    volume = scrapy.Field()
    price = scrapy.Field()
