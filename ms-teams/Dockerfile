# Build Stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY ms-shared/pom.xml ./ms-shared/pom.xml
COPY ms-shared/src ./ms-shared/src
RUN mvn -q install -f ms-shared/pom.xml -DskipTests

COPY ms-teams/pom.xml ./ms-teams/pom.xml
RUN mvn -q -e dependency:go-offline -f ms-teams/pom.xml
COPY ms-teams/src ./ms-teams/src
RUN mvn -q package -DskipTests -f ms-teams/pom.xml

#Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/ms-teams/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]