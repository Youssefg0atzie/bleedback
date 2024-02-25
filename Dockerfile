FROM openjdk:17
EXPOSE 8001
ADD target/clt-0.0.1.jar clt-0.0.1.jar
ENTRYPOINT ["java","-jar","clt-0.0.1.jar"]