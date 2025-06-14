# Fase de construcción (con Java 17)
FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app
COPY . .

# Ejecuta Gradle DENTRO del contenedor (con Java 17)
RUN ./gradlew clean bootJar

# Fase de ejecución (también con Java 17)
FROM eclipse-temurin:17-jre-jammy  # Versión ligera solo para ejecución
WORKDIR /app
COPY --from=builder /app/build/libs/ms-citas-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]