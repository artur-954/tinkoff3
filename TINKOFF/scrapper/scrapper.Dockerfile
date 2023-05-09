FROM openjdk:11-jre-slim
WORKDIR /app
COPY scrapper/target/scrapper.jar app.jar
CMD ["java", "-jar", "app.jar"]