import os

DATABASE_URL="postgresql://postgres:" + os.environ.get("RECOMMEND_DB_PASSWORD")+"@" + os.environ.get("RECOMMEND_DB_HOST")+":5432/mldataset"
print(DATABASE_URL)