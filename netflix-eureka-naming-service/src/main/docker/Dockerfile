FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD maven/${project.build.finalName}.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${PROFILE}","-jar","/app.jar"]