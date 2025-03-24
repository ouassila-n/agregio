# Utiliser l'image de base Amazon Corretto 21
FROM amazoncorretto:21

# Définir l'argument pour le chemin du fichier JAR
ARG JAR_FILE=target/*.jar

# Copier le fichier JAR depuis le contexte de build dans l'image Docker
COPY ${JAR_FILE} application.jar

# Définir la commande par défaut pour exécuter l'application Java
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]