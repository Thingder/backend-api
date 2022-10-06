FROM openjdk:11
ARG JAR_FILE=build/libs/thingder-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "-Dcom.amazonaws.sdk.disableEc2Metadata=true", "-Duser.timezone=GMT+09:00"]
EXPOSE 8080
