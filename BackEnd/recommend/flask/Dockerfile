# 빌드에 필요한 베이스 이미지를 파이썬 3.11로 지정
FROM python:3.11.1


# Spark 관련 의존성 설치
RUN apt-get update && \
    apt-get install -y default-jdk-headless wget && \
    apt-get clean

# Spark 관련 환경변수 설정
ENV SPARK_VERSION=3.4.1
ENV SPARK_HOME=/opt/spark
ENV HADOOP_VERSION=3

# Spark 설치
# Spark 다운로드 및 설치
RUN wget https://dlcdn.apache.org/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz && \
    tar xzf spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz && \
    mv spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION} ${SPARK_HOME} && \
    rm spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz

# flask 프로젝트 폴더로 작업영역 변경
WORKDIR /BackEnd/recommend/flask

# requirements.txt에 나열된 패키지들을 설치
RUN pip install --no-cache-dir -r requirements.txt

# 플라스크 서버 실행
CMD ["python","-m","flask","run"]
