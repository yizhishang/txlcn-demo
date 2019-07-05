FROM gtavali/alpine-oraclejdk8:latest

VOLUME /tmp
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ARG JAR_FILE
ADD ${JAR_FILE} app.jar

ENV JAVA_OPTS=""

EXPOSE 8080 8080

ENTRYPOINT ["java","-jar", "/app.jar","-Duser.timezone=GMT+08 ${JAVA_OPTS}", "-Djava.security.egd=file:/dev/./urandom"]