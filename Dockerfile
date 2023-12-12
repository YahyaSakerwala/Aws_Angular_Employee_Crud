FROM openjdk:17
EXPOSE 5000
ADD target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT [ "java","-jar","/spring-boot-docker.jar"]

