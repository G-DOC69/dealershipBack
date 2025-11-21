# =======================================
# Build Stage (Debian + JDK 25 + Maven)
# =======================================
FROM debian:bookworm AS build
WORKDIR /app

# Install dependencies
RUN apt-get update && \
    apt-get install -y wget tar gzip curl

# Install JDK 25 manually
RUN wget https://download.java.net/java/early_access/jdk25/31/GPL/openjdk-25-ea+31_linux-x64_bin.tar.gz && \
    tar -xzf openjdk-25-ea+31_linux-x64_bin.tar.gz -C /opt && \
    ln -s /opt/jdk-25 /opt/java && \
    ln -s /opt/java/bin/java /usr/bin/java && \
    ln -s /opt/java/bin/javac /usr/bin/javac

# Install Maven manually
RUN wget https://downloads.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz && \
    tar -xzf apache-maven-3.9.9-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.9.9 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn

# Verify installs
RUN java -version && mvn -version

# Copy project
COPY pom.xml .
COPY src ./src

# Build JAR
RUN mvn -q -B package -DskipTests

# ===========================
# Run Stage (JRE 25)
# ===========================
FROM debian:bookworm

# Install JDK 25 runtime
RUN apt-get update && \
    apt-get install -y wget tar gzip curl && \
    wget https://download.java.net/java/early_access/jdk25/31/GPL/openjdk-25-ea+31_linux-x64_bin.tar.gz && \
    tar -xzf openjdk-25-ea+31_linux-x64_bin.tar.gz -C /opt && \
    ln -s /opt/jdk-25 /opt/java && \
    ln -s /opt/java/bin/java /usr/bin/java

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
