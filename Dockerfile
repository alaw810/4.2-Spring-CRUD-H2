# ===============================
# 🏗️ Stage 1 — Build the application
# ===============================
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy only pom.xml first to cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the application (skip tests for faster builds)
RUN mvn clean package -DskipTests


# ===============================
# 🚀 Stage 2 — Production runtime
# ===============================
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy only the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 9000

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
