# Agregio Project

## Description

Ce projet est une application Spring Boot qui gère les offres, les parcs et les blocs d'énergie. Il utilise une base de données Postgresql dans un docker pour stocker les informations et Maven pour la gestion des dépendances.


## Utilisation

1. Démarrez docker-compose pour lancer la base de données Postgresql au préalable.:

    ```bash
    docker-compose up
    ```

2. L'application sera disponible à l'adresse `http://localhost:8080/swagger-ui/index.html`.

## Structure du projet

- `src/main/java/com/example/agregio` : Contient le code source de l'application.
- `src/main/resources` : Contient les fichiers de configuration.
- `src/test/java/com/example/agregio` : Contient les tests unitaires.

## Technologies utilisées

java 21
Spring Boot 3.4.4
