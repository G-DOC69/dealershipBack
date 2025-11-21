# ===========================
# Build Stage (Install Maven on JDK 25)
# ===========================
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# Install Maven manually
RUN apt-get update && \
    apt-get install -y wget tar && \
    wget https://downloads.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz && \
    tar -xzf apache-maven-3.9.9-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.9.9 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn

# Optional: verify Maven works
RUN mvn -v

# Copy Maven project
COPY pom.xml .
COPY src ./src

# Build the Spring Boot app
RUN mvn -q -B package -DskipTests

# ===========================
# Run Stage (JRE 25)
# ===========================
FROM eclipse-temurin:25-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
