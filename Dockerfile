FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/examen-hito-0.0.1-SNAPSHOT.jar examen-hito-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/examen-hito-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080