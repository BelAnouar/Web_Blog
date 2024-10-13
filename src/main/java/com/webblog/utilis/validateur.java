package com.webblog.utilis;

import com.webblog.models.Article;
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

    private static boolean validerAuteur(Auteur auteur) {
        return auteur != null && auteur.getId() != null;
    }
}
