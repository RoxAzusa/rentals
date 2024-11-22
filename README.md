
# Rentals

Un portail web pour la mise en relation entre locataires et propriétaires, développé en **Java** avec **Spring Boot**.

---

## Description

Rentals est une application qui simplifie la communication entre locataires et propriétaires. Elle propose une gestion centralisée des informations et des interactions locatives via une interface intuitive et moderne.

---

## Prérequis

Avant de commencer, assurez-vous d'avoir installé les outils suivants :  
- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)  
- [Maven](https://maven.apache.org/download.cgi)  
- [Git](https://git-scm.com/)  
- Un IDE comme [Eclipse](https://www.eclipse.org/) ou [IntelliJ IDEA](https://www.jetbrains.com/idea/)

---

## Installation du projet

### Étape 1 : Cloner le dépôt
Clonez le dépôt GitHub et accédez au répertoire du projet :  
```bash
git clone https://github.com/RoxAzusa/rentals.git
cd rentals
```

### Étape 2 : Configurer les variables d'environnement (sous Windows)
Afin de sécuriser les informations sensibles, les identifiants de la base de données et la clé JWT sont configurés comme variables d'environnement système.

1. Configurer les variables :

Définissez les variables d'environnement suivantes sur votre machine :
- Allé dans les `Paramètres` -> `Système` -> `Informations système` -> `Paramètres avancés du système`
- Dans la fenêtre `Propriétés système` allé dans l'onglet `Paramètres système avancés` puis dans `Variables d'environnement...`
- Dans la section `Variable système` ajouter ces 2 nouvelles variables :
    ```
    spring.datasource.password=yourDatabasePassword
    security-config.jwt-key=yourJWTSecretKey
    ```
- Validez les modifications, puis redémarrez votre terminal ou votre système si nécessaire.

### Étape 3 : Configurer la base de données
1. Personnalisez la connexion à la base de données en modifiant le fichier `application.properties` :  
   ```properties
   # Exemple de configuration pour MySQL
   spring.datasource.url=jdbc:mysql://localhost:3306/rentals
   spring.datasource.username=yourUsername
   ```

2. Créez une base de données nommée `rentals` :  
     ```sql
     CREATE DATABASE rentals;
     ```

3. Initalisez la base de données avec le `script.sql` fournit dans le dossier **ressources\sql**

4. Assurez-vous que le service MySQL est démarré et que les identifiants configurés dans `application.properties` sont corrects.

### Étape 4 : Construire et exécuter le projet
Installez les dépendances Maven et lancez l'application :  
```bash
mvn clean install
mvn spring-boot:run
```

Remarque : Si vous changez le port par défaut (8080), assurez-vous de mettre à jour les fichiers suivants  :
- Partie back-end :
    - `application.properties`
    ```properties
    rentals.image.base-url=http://localhost:8080/api/rentals/image/
    ```
- Partie front-end :
    - `proxy.config.json`
    ```
    "target": "http://localhost:8080",
    ```
    - `environment.prod.ts`
    ```
    baseUrl: 'localhost:8080/api/',
    ```
    - `environment.ts`
    ```
    baseUrl: 'localhost:8080/api/',
    ```

---

## URL de l'application

### Accéder à l'application
Une fois l'application démarrée, elle est accessible à cette adresse :  
```
http://localhost:8080
```

### Documentation Swagger
L'API est documentée via Swagger. Vous pouvez consulter la documentation interactive ici :  
```
http://localhost:8080/swagger-ui.html
```

---

## Fonctionnalités principales

- Gestion des utilisateurs : locataires et propriétaires.
- Inscription, connexion et authentification utilisateur.
- Communication et gestion des relations locatives.

---

## Auteur

- **Roxane Crépeaux**  
  [GitHub](https://github.com/RoxAzusa)
