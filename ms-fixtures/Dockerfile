# Build Stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY ms-shared/pom.xml ./ms-shared/pom.xml
COPY ms-shared/src ./ms-shared/src
RUN mvn -q install -f ms-shared/pom.xml -DskipTests

COPY ms-fixtures/pom.xml ./ms-fixtures/pom.xml
RUN mvn -q -e dependency:go-offline -f ms-fixtures/pom.xml
COPY ms-fixtures/src ./ms-fixtures/src
RUN mvn -q package -DskipTests -f ms-fixtures/pom.xml

#Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/ms-fixtures/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]