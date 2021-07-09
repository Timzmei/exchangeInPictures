FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE=build/libs/exchange_in_pictures-0.0.1.jar
WORKDIR /build/libs
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]