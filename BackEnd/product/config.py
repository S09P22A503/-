import os

DATABASE_URL="mysql+pymysql://root:" + os.environ.get("PRODUCT_DB_PASSWORD")+"@" + os.environ.get("PRODUCT_DB_HOST")+":3306/sshmarket"
print(DATABASE_URL)