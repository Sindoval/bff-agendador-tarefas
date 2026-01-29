FROM maven:3.9.5-eclipse-temurin-17-alpine AS BUILD
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar ./bff.jar
RUN apk add --no-cache tzdata
ENV TZ=America/Sao_Paulo

EXPOSE 8084

CMD ["java", "-jar", "/app/bff.jar"]