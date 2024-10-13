# 🌐 Application Web de Gestion de Contenu

## Description du projet
Cette application est une refonte d'une solution console en une application web interactive et participative, permettant de gérer des articles de blog, des commentaires et des auteurs. Développée en Java, elle utilise une base de données relationnelle et suit une architecture MVC pour assurer modularité et maintenabilité.

## Objectif général de l'application
Créer une application web Java permettant la gestion des articles, commentaires et utilisateurs, avec des fonctionnalités CRUD complètes et une interface intuitive, tout en respectant les bonnes pratiques de développement logiciel.

## 🛠️ Technologies utilisées
- **Langage** : Java 8
- **Framework Frontend** : HTML, CSS (avec un framework CSS)
- **Backend** : Servlet, JSP, JSTL, Hibernate (JPA)
- **Gestion de dépendances** : Maven
- **Serveur d'applications** : Tomcat
- **Base de données** : MySQL
- **Gestion de projet** : Jira (méthodologie Scrum), Git (git-flow)
- **Tests unitaires** : JUnit, Mockito (TDD)

## 📂 Structure du projet
- **Modèle (Model)** : Gère la logique métier et la persistance des données (JPA, Hibernate).
- **Vue (View)** : Interface utilisateur (JSP, JSTL).
- **Contrôleur (Controller)** : Servlets qui coordonnent la logique entre le modèle et la vue.
- **DAO (Data Access Object)** : Gestion de l'accès aux données.
- **Service** : Couches de service pour gérer la logique métier.
  
## 📊 Architecture adoptée
L'application suit une architecture MVC en couches, avec les couches **Présentation**, **Métier**, **DAO**, **Service**, **Repository** et **Utilitaires**. Elle utilise des **Design Patterns** comme Singleton et Repository.

## 📝 Fonctionnalités
### Page de gestion des articles
- Liste avec pagination des articles
- Création, modification, suppression des articles
- Recherche d’articles par titre
- Affichage du nombre de commentaires par article

### Page de gestion des commentaires
- Liste des commentaires par article
- Ajout, modification, suppression de commentaires
- Assignation d’un statut (approuvé, rejeté)

### Page de gestion des auteurs
- Liste des auteurs avec pagination
- Ajout, modification et suppression des auteurs
- Assignation de rôles (Contributeur, Éditeur)

## ⚙️ Instructions d'installation et d'utilisation
### Prérequis
- Java 8+
- Maven
- MySQL
- Tomcat 9.x

### Installation
1. **Cloner le dépôt** :
    ```bash
    git clone https://github.com/BelAnouar/Web_Blog.git
    cd votre-projet
    ```

2. **Configurer la base de données** :
   - Créer une base de données MySQL nommée `gestion_contenu`.
   - Exécuter le script SQL fourni (`/scripts/db-init.sql`) pour créer les tables et insérer les données initiales.

3. **Configurer le fichier `src/main/resources/hibernate.cfg.xml`** avec vos identifiants MySQL.

4. **Compiler et déployer le projet avec Maven** :
    ```bash
    mvn clean install
    ```

5. **Déployer sur Tomcat** :
    - Copier le fichier WAR généré (`target/gestion-contenu.war`) dans le répertoire `webapps` de Tomcat.

6. **Démarrer Tomcat** et accéder à l'application à l'adresse suivante : `http://localhost:8080/web-blogs`.

## Planification
https://web-blog.atlassian.net/jira/software/c/projects/WB/boards/1/backlog?assignee=712020%3A352fe240-f3ce-4291-b7af-1c1eca455c33&epics=visible

### ⚡ Lancer les tests
Les tests unitaires sont disponibles et peuvent être exécutés avec Maven :
```bash
mvn test


## 🖼️ Captures d'écran

- Page de gestion des articles :
<img width="446" alt="Cruds" src="https://github.com/user-attachments/assets/4b0f6f7a-58f3-4f7d-9111-6452f9589dff">
<img width="353" alt="articles" src="https://github.com/user-attachments/assets/a4429744-086d-4f71-ab21-c49419d404be">

- Page de gestion des auteurs :
<img width="518" alt="CRUD" src="https://github.com/user-attachments/assets/cc34a353-b82c-4993-9fb0-fac9ee34942d">
<img width="335" alt="auteur" src="https://github.com/user-attachments/assets/40f9d50c-8e6f-4332-989a-32aae79a0e52">

- Page de gestion des commentaires :

## 💡 Améliorations futures possibles
Intégration de fonctionnalités de tri et de filtre avancés pour les articles.
Ajout d'une gestion des catégories pour les articles.
Notification par email lors de la publication d’un article ou d’un commentaire approuvé.
Implémentation d’un système de cache pour optimiser les performances.


