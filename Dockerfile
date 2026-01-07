# Multi-stage Dockerfile for a Maven (Spring) app
# Adjust JDK/Maven versions if your project requires a different Java version.

FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /workspace

# Copy files required for dependency resolution first to leverage Docker cache
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Pre-fetch dependencies (speeds rebuilds)
RUN mvn -B -DskipTests dependency:go-offline

# Copy source and build the project (skip tests for faster builds; remove -DskipTests to run them)
COPY src ./src
RUN mvn -B -DskipTests package

# Runtime image: lightweight JRE
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy the built jar (assumes a runnable/executable jar is produced in target/)
COPY --from=builder /workspace/target/*.jar /app/app.jar

EXPOSE 8080
ENV JAVA_OPTS=""

ENTRYPOINT ["sh","-c","exec java $JAVA_OPTS -jar /app/app.jar"]