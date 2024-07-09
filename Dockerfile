FROM eclipse-temurin:17-jre-alpine

ARG JAR_FILE

COPY ${JAR_FILE} balance-converter-service.jar

EXPOSE 8080

CMD ["java", "-jar", "balance-converter-service.jar"]
