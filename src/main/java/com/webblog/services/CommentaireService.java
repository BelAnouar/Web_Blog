package com.webblog.services;

import java.time.LocalDate;


import com.webblog.enums.StatusC;
import com.webblog.models.Article;
import com.webblog.models.Commontaire;
import com.webblog.repositories.impl.CommantaireImpl;

public class CommentaireService {
	 private final CommantaireImpl CommantaireImpl;

	    public CommentaireService() {
	        this.CommantaireImpl = new CommantaireImpl();
	    }

	    public boolean ajouterCommentaire(String contenu, Article article) {
	  
	        Commontaire commentaire = new Commontaire();
	        commentaire.setContenu(contenu);
	        commentaire.setDateCreation(LocalDate.now()); 
	        commentaire.setArticle(article);
	        commentaire.setStatus(StatusC.Approuvé);
	        
	        
	        return CommantaireImpl.save(commentaire);
	    }
	    public static void main(String[] args) {
	    	String contenu = "fhjkhfkjsdhfjk  ifhfkljslfjlkdsj";
	    	CommentaireService commentaireService =new CommentaireService();
	        int articleId = 1;

	       
	        ArticleService articleDAO = new ArticleService();
	        Article article = articleDAO.findById(articleId);

	       
	        boolean success = commentaireService.ajouterCommentaire(contenu, article);

		}
}
