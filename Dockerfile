# Utiliser l'image officielle OpenJDK
FROM eclipse-temurin:21-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR dans le conteneur
COPY target/*.jar app.jar

# Exposer le port de l'application
EXPOSE 8080

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
