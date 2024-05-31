
FROM ubuntu:22.04

# 安装OpenJDK 18
RUN apt-get update && apt-get install -y openjdk-18-jdk

# 安装Python和pip
RUN apt-get update && apt-get install -y python3 python3-pip

# 安装Python库
RUN pip3 install pymysql openai
# 创建/app目录

RUN mkdir /app


# 将Python文件复制到容器中

COPY ai.py /app/ai.py


# 将JAR包复制到容器中

COPY target/cs304-0.0.1-SNAPSHOT.jar /app/back.jar


# 设置工作目录

WORKDIR /app


# 运行Python文件

# CMD ["python", "your-python-file.py"]


# 或者，如果你想运行JAR包，可以取消注释下面的行

CMD ["java", "-jar", "back.jar"]

