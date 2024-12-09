FROM openjdk:21
ARG JAR_FILE=build/libs/root-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","app.jar", "-DJSKEY=${JASYPT_KEY}"]