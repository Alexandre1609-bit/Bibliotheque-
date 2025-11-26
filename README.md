Bibliothèque Web - Application Full Stack

Une application web de gestion de bibliothèque permettant de gérer les utilisateurs, les livres et les emprunts. Ce projet a été réalisé en autodidacte pour préparer ma candidature au BUT Informatique, avec pour objectif de comprendre les fondamentaux de l'architecture Web sans utiliser de frameworks ORM. 

Fonctionnalités

    Consultation : Affichage dynamique de la liste des livres disponibles (Fetch API). 
    Authentification Sécurisée : Inscription utilisateurs. Connexion sécurisée.
    Sécurité Custom : Implémentation manuelle du hachage SHA-256 avec Salage (Salt). 
    Gestion des Emprunts : Emprunter un livre. Rendre un livre (mise à jour de la date de retour réelle). 
    Administration : Anonymisation des utilisateurs (RGPD).


Architecture Technique
 
    Le projet respecte une architecture en couches stricte pour assurer la maintenance et l'évolutivité : 
    
    Back-End (Java / Spring Boot) 
    Controller : Gestion des requêtes HTTP (REST API) et renvoi de JSON.
    DAO (Data Access Object) : Gestion des interactions SQL via JDBC et PreparedStatement (protection contre les injections SQL).
    Model : Objets Java simples (POJO) représentant les tables (User, Book, Loan).
    Utils : Gestionnaire de sécurité pour le hachage des mots de passe.

    Front-End (Vanilla JS)
    HTML5 / CSS3 : Structure et design.
    JavaScript : Communication asynchrone avec l'API Java via fetch(). Manipulation du DOM pour l'affichage dynamique.
    Base de DonnéesMySQL : Stockage relationnel avec contraintes d'intégrité (Clés étrangères).


Installation et Lancement

    Prérequis
    JDK 17 ou supérieur.
    MySQL Server.
    Maven.

    1. Configuration de la Base de Données

    Créez une base de données nommée "library" et exécutez le script SQL (disponible dans src/main/resources/schema.sql ou ci-dessous) :

    SQLCREATE DATABASE library;
    USE library;

    CREATE TABLE books (
        books_id INT NOT NULL AUTO_INCREMENT,
        title VARCHAR(100),
        author VARCHAR(100),
        stock INT,
        image_url VARCHAR(255),
        PRIMARY KEY (books_id)
    );

    CREATE TABLE users (
        user_id INT NOT NULL AUTO_INCREMENT,
        name VARCHAR(100),
        email VARCHAR(100),
        password CHAR(64),
        salt CHAR(32),
        PRIMARY KEY (user_id)
    );

    CREATE TABLE loans (
        loan_id INT NOT NULL AUTO_INCREMENT,
        borrow_date DATE,
        return_date DATE,
        return_date_real DATE,
        books_id_fk INT,
        user_id_fk INT,
        PRIMARY KEY (loan_id),
        FOREIGN KEY (books_id_fk) REFERENCES books(books_id),
        FOREIGN KEY (user_id_fk) REFERENCES users(user_id)
    );

    2. Configuration de l'application

    Ouvrez le fichier "src/main/resources/application.properties" et mettez à jour vos identifiants MySQL :

    Propertiesspring.datasource.url=jdbc:mysql://localhost:3306/library
    spring.datasource.username=VOTRE_USER
    spring.datasource.password=VOTRE_MDP

    3. Lancement

    Lancez l'application via votre IDE (classe BibliothequeWebApplication) ou en ligne de commande : Bash./mvnw spring-boot:run
    Accédez ensuite à l'application via : http://localhost:8080/index.html


API Endpoints

    (Méthode, URL, Description)

    GET/livres Récupère la liste des livres (JSON).
    POST/sign-in Inscription d'un nouvel utilisateur.
    POST/login Connexion utilisateur.
    POST/emprunts Créer un nouvel emprunt.
    POST/retours Valider le retour d'un livre.
    POST/anonymize Anonymiser un utilisateur (RGPD).


 Auteur du projet
 
    réalisé par Alexandre.
    
    Objectif : Apprentissage autodidacte avec pour objectif d'intégrer un BUT Informatique.