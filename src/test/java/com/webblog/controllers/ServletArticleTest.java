package com.webblog.controllers;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webblog.enums.Status;
import com.webblog.models.Article;
import com.webblog.models.Auteur;
import com.webblog.services.ArticleService;

public class ServletArticleTest {
	private ArticleService articleService ;
	@Before
	public void setUp() throws Exception {
		articleService=new ArticleService();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
	     Article article = new Article();
	        article.setTitre("Test ocp");
	        article.setContenu("ocp Content");
	        article.setDateCreation(LocalDate.now());
	        article.setDatePublication(LocalDate.now());
	        article.setStatut(Status.Publie);

	        Auteur auteur = new Auteur();
	        auteur.setId(1);
	        article.setAuteur(auteur);
   System.out.println(articleService.save(article));
	}

}
