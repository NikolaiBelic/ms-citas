# Usa la imagen de Java 17 (como en tu entorno local)
FROM eclipse-temurin:17-jdk-jammy

# Directorio de trabajo
WORKDIR /app

# Copia el JAR generado LOCALMENTE (ms-citas-0.0.1-SNAPSHOT.jar)
COPY build/libs/ms-citas-0.0.1-SNAPSHOT.jar app.jar

# Puerto expuesto (ajusta si tu app usa otro)
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]