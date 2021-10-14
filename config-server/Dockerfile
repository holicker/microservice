FROM openjdk:8u302-jdk

EXPOSE 8888

ADD ./build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]