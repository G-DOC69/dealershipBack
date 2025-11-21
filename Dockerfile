# =====================================
# Build Stage (Maven + JDK 25)
# =====================================
FROM maven:3.9.6-eclipse-temurin-25 AS build
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build Spring Boot application
RUN mvn -q -B package -DskipTests

# =====================================
# Run Stage (JRE 25)
# =====================================
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Koyeb dynamic port support
ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
