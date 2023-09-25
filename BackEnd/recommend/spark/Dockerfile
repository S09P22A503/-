# 빌드에 필요한 베이스 이미지를 파이썬 3.8로 지정
FROM python:3.8

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
    
# Spark S3 사용에 필요한 라이브러리 버전
ENV HADOOP_AWS_VERSION=3.2.4
ENV AWS_JDK_VERSION=1.11.901

# Spark S3사용에 필요한 의존성 설치
RUN wget https://repo1.maven.org/maven2/org/apache/hadoop/hadoop-aws/${HADOOP_AWS_VERSION}/hadoop-aws-${HADOOP_AWS_VERSION}.jar && \
    mv hadoop-aws-${HADOOP_AWS_VERSION}.jar ${SPARK_HOME}/jars && \
    wget https://repo1.maven.org/maven2/com/amazonaws/aws-java-sdk-bundle/${AWS_JDK_VERSION}/aws-java-sdk-bundle-${AWS_JDK_VERSION}.jar && \
    mv aws-java-sdk-bundle-${AWS_JDK_VERSION}.jar ${SPARK_HOME}/jars


# PostgreSQL 사용을 위한 Driver 경로 환경변수 (Driver 추가시 이 경로로 슈루룩)
ENV SPARK_DRIVER_CLASSPATH=${SPARK_HOME}/driver
ENV POSTGRESQL_VERSION='42.6.0'

# PostgreSQL 사용을 위한 Driver 설치
RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/${POSTGRESQL_VERSION}/postgresql-${POSTGRESQL_VERSION}.jar && \
    mkdir -p ${SPARK_DRIVER_CLASSPATH} && \
    mv postgresql-${POSTGRESQL_VERSION}.jar ${SPARK_DRIVER_CLASSPATH}
    
# 소스코드 복사
COPY ./BackEnd/recommend/spark /model
WORKDIR /model

# requirements.txt에 나열된 패키지들을 설치
RUN pip install --no-cache-dir -r requirements.txt

# 나머지 애플리케이션 코드 컨테이너에 복사
CMD ["python","SparkSession.py"]
