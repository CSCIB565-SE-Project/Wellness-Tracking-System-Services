FROM maven:3.8.5 AS builder
WORKDIR /build
COPY pom.xml .
COPY green-mercury-414423-c3e201744892.json .
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:21
WORKDIR /opt
RUN apt-get update \
  && apt-get install --no-install-recommends -y \
    wget \
  ;
RUN curl -o cloud-sql-proxy curl -o cloud-sql-proxy https://storage.googleapis.com/cloud-sql-connectors/cloud-sql-proxy/v2.10.1/cloud-sql-proxy.linux.amd64
RUN chmod +x cloud-sql-proxy
COPY --from=builder /build/green-mercury-414423-c3e201744892.json /opt/green-mercury-414423-c3e201744892.json
RUN ./cloud-sql-proxy --credentials-file /opt/green-mercury-414423-c3e201744892.json green-mercury-414423:us-central1:wellness &
COPY --from=builder /build/target/*.jar /opt/app.jar
ENV PORT 8080
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar