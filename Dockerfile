FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
