# 빌드에 필요한 베이스 이미지를 파이썬 3.8로 지정
FROM python:3.8-slim

# Spark 관련 의존성 설치
RUN apt-get update && \
    apt-get install -y default-jdk-headless wget && \
    apt-get clean

# Spark 관련 환경변수 섫정
ENV SPARK_VERSION=3.4.1
ENV SPARK_HOME=/opt/spark
ENV HADOOP_VERSION=3

# Spark 설치
# Spark 다운로드 및 설치
RUN wget https://dlcdn.apache.org/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz && \
    tar xzf spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz && \
    mv spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION} ${SPARK_HOME} && \
    rm spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz

# PostgreSQL 사용을 위한 Driver 경로 환경변수 (Driver 추가시 이 경로로 슈루룩)
ENV SPARK_DRIVER_CLASSPATH='/etc/spark/driver'
ENV POSTGRESQL_VERSION='42.6.0'
# PostgreSQL 사용을 위한 Driver 설치
RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/${POSTGRESQL_VERSION}/postgresql-${POSTGRESQL_VERSION}.jar && \
    mv postgresql-${POSTGRESQL_VERSION}.jar ${SPARK_DRIVER_CLASSPATH} && \
    rm postgresql-${POSTGRESQL_VERSION}.jar 

# requirements.txt에 나열된 패키지들을 설치
RUN pip install --no-cache-dir -r requirements.txt

# 나머지 애플리케이션 코드 컨테이너에 복사
CMD ["python","SparkSession.py"]
