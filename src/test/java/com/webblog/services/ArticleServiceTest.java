package com.webblog.services;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webblog.enums.Status;
import com.webblog.models.Article;
import com.webblog.models.Auteur;

public class ArticleServiceTest {
	public ArticleService  articleService;

	@Before
	public void setUp() throws Exception {
		articleService= new ArticleService();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		   Article article = new Article();
	        article.setTitre("sevices");  
	        article.setContenu("Test Content");  
	        article.setDateCreation(LocalDate.now());
	        article.setDatePublication(LocalDate.now());
	        article.setStatut(Status.Publié); 
	        
	        Auteur auteur = new Auteur();
	        auteur.setId(1);  
	        article.setAuteur(auteur);
		 articleService.save(article);
	}


	@Test
	public void testUpdate() {
	    Article article = new Article();
	    article.setId(6);  
	    article.setTitre("anwar");
	    article.setContenu("Test Content");
	    article.setDateCreation(LocalDate.now());
	    article.setDatePublication(LocalDate.now());
	    article.setStatut(Status.Brouillon);

	    Boolean result = articleService.update(article);

	    assertTrue("Article should be updated successfully", result);

	  
	}



	@Test
	public void testDelete() {
		  Article article = new Article();
		  article.setId(15);
	 articleService.delete(article.getId());
	}

}
