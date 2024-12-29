# Imagen base de Java 21
FROM openjdk:21-jdk-slim

# Crear un directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado
COPY target/school-management-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
