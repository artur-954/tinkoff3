FROM openjdk:11-jre-slim
WORKDIR /app
COPY bot/target/bot.jar app.jar
CMD ["java", "-jar", "app.jar"]