FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B -q
COPY src ./src
RUN mvn package -DskipTests -B -q

FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S carde && adduser -S carde -G carde
USER carde
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENV JAVA_OPTS="-XX:+UseSerialGC -Xmx256m -Xms128m -XX:MaxMetaspaceSize=96m \
  -Djava.security.egd=file:/dev/./urandom \
  -Dspring.profiles.active=prod"
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
