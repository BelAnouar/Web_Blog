


DROP DATABASE IF EXISTS `blog_management`;
CREATE DATABASE IF NOT EXISTS `blog_management`;
USE `blog_management`;

CREATE TABLE `article` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`titre` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`contenu` TEXT NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`dateCreation` DATE NOT NULL,
	`datePublication` DATE NULL DEFAULT NULL,
	`statut` ENUM('Brouillon','Publie') NOT NULL DEFAULT 'Brouillon' COLLATE 'utf8mb4_0900_ai_ci',
	`auteur_id` INT(10) NOT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `auteur_id` (`auteur_id`) USING BTREE,
	INDEX `idx_article_titre` (`titre`) USING BTREE,
	INDEX `idx_article_statut` (`statut`) USING BTREE,
	INDEX `idxarticletitre` (`titre`) USING BTREE,
	CONSTRAINT `article_ibfk_1` FOREIGN KEY (`auteur_id`) REFERENCES `auteur` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)

;


CREATE TABLE IF NOT EXISTS `auteur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `dateNaissance` date NOT NULL,
  `role` enum('Contributeur','Editeur') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `uc_email` (`email`),
  CONSTRAINT `chk_role` CHECK ((`role` in ('Contributeur','Editeur')))
) ;


CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int NOT NULL AUTO_INCREMENT,
  `contenu` text NOT NULL,
  `dateCreation` date NOT NULL,
  `statut` enum('Approuvé','Rejeté') NOT NULL DEFAULT 'Approuvé',
  `article_id` int NOT NULL,
  `auteur_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `article_id` (`article_id`),
  KEY `auteur_id` (`auteur_id`),
  CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `commentaire_ibfk_2` FOREIGN KEY (`auteur_id`) REFERENCES `auteur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ;

CREATE USER 'bloguser'@'localhost' IDENTIFIED BY 'password';
------------
GRANT SELECT, INSERT, UPDATE, DELETE ON Auteur TO 'bloguser'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON Article TO 'bloguser'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON Commentaire TO 'bloguser'@'localhost';