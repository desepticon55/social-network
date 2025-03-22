FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY social-network-impl/target/social-network-impl.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]