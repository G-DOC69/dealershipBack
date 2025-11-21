# ===========================
# Build Stage (JDK 25 + Maven)
# ===========================
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

# Build Spring Boot (try mvnw first, fallback to Maven)
RUN ./mvnw -q -B package -DskipTests || mvn -q -B package -DskipTests

# ===========================
# Run Stage (JRE 25)
# ===========================
FROM eclipse-temurin:25-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
