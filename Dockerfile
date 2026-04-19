FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -Dmaven.test.skip=true

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "target/examforge-0.0.1-SNAPSHOT.jar"]