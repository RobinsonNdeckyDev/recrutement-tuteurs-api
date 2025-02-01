# Étape 1 : Builder l'application avec Maven
FROM maven:3.8.6-openjdk-21 AS build
WORKDIR /app

# Copier les fichiers pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline

# Copier le reste du code source
COPY src ./src

# Compiler et packager l'application
RUN mvn clean package -DskipTests

# Étape 2 : Créer l'image finale
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copier le jar généré depuis l'étape précédente
COPY --from=build /app/target/*.jar app.jar

# Exposer le port (en utilisant la variable d'environnement PORT si disponible)
EXPOSE ${PORT:-8080}

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
