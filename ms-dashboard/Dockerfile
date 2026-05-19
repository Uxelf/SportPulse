# Build Stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY ms-shared/pom.xml ./ms-shared/pom.xml
COPY ms-shared/src ./ms-shared/src
RUN mvn -q install -f ms-shared/pom.xml -DskipTests

COPY ms-dashboard/pom.xml ./ms-dashboard/pom.xml
RUN mvn -q -e dependency:go-offline -f ms-dashboard/pom.xml
COPY ms-dashboard/src ./ms-dashboard/src
RUN mvn -q package -DskipTests -f ms-dashboard/pom.xml

#Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/ms-dashboard/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]