FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY src/main/java .

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/*.jar"]