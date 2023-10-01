# 빌드에 필요한 베이스 이미지를 파이썬 3.11로 지정
FROM python:3.11

# 파이썬 소스코드 복사
COPY . /app

# 작업환경 변경
WORKDIR /app

# pip 버전 업그레이드
RUN pip install -upgrade pip && pip install —upgrade setuptools wheel

# requirements.txt에 나열된 패키지들을 설치
RUN pip install --no-cache-dir -r requirements.txt

# 전체 크롤링 실행
CMD ["scrapy","crawl","agromarket"]