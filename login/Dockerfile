FROM maven:3.8.5 AS builder
WORKDIR /build
COPY ./login/pom.xml .
COPY ./login/src ./src
RUN mvn clean package

FROM eclipse-temurin:21
WORKDIR /opt
COPY --from=builder /build/target/*.jar /opt/app.jar
ENV PORT 8000
EXPOSE 8000
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar