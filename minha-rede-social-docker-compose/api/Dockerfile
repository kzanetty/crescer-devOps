FROM openjdk:11-jre-slim
WORKDIR /app
COPY api/target/berk-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "/app/berk-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
