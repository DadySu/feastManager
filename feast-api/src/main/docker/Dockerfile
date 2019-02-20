#构建环境，使用已经有的jdk环境
FROM openjdk:latest

#维护者
MAINTAINER liub

#临时挂载区（docker容器中的一个具有持久化存储功能的目录，可与其他容器共享）
VOLUME /tmp

#设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

#将jar包添加到容器中
ADD feast-api.jar feast.jar

#执行授权
RUN sh -c 'touch /feast.jar'

#暴露端口
EXPOSE 8080

#启动项目
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /feast.jar" ]