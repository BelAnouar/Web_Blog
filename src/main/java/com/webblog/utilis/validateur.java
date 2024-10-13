package com.webblog.utilis;

import com.webblog.models.Article;
import com.webblog.enums.Role;
import com.webblog.enums.Status;
import com.webblog.models.Auteur;
import java.time.LocalDate;

public class validateur {

    public static boolean validerArticle(Article article) {
        return validerTitre(article.getTitre()) &&
               validerContenu(article.getContenu()) &&
               validerDates(article.getDateCreation(), article.getDatePublication()) &&
               validerStatut(article.getStatut()) &&
               validerAuteur(article.getAuteur());
    }

    private static boolean validerTitre(String titre) {
        return titre != null && !titre.trim().isEmpty() && titre.length() <= 100;
    }

    private static boolean validerContenu(String contenu) {
        return contenu != null && !contenu.trim().isEmpty();
    }

    private static boolean validerDates(LocalDate dateCreation, LocalDate datePublication) {
        return dateCreation != null && (datePublication == null || !datePublication.isBefore(dateCreation));
    }

    private static boolean validerStatut(Status statut) {
        return statut != null;
    }

    public static boolean validerAuteur(Auteur auteur) {
        return validerNom(auteur.getNom()) &&
               validerPrenom(auteur.getPrenom()) &&
               validerEmail(auteur.getEmail()) &&
               validerDateNaissance(auteur.getDateNaissance());
    }

    private static boolean validerNom(String nom) {
        return nom != null && !nom.trim().isEmpty() && nom.length() <= 50;
    }

    private static boolean validerPrenom(String prenom) {
        return prenom != null && !prenom.trim().isEmpty() && prenom.length() <= 50;
    }

    private static boolean validerEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private static boolean validerDateNaissance(LocalDate dateNaissance) {
        return dateNaissance != null && !dateNaissance.isAfter(LocalDate.now());
    }

    
}
