FROM openjdk:17-ea-slim-buster

MAINTAINER ruikai

ENV PARAMS=""

ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD /chatgpt-bot-interfaces/target/chatbot-api.jar /chatbot-api.jar

ENTRYPOINT ["sh", "-c", "java -jar $JAVA_OPTS /chat-api.jar $PARAMS"]
