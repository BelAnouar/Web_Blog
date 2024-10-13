package com.webblog.models;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webblog.enums.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ArticleTest {
	   private EntityManagerFactory entityManagerFactory;
	    private EntityManager entityManager;

	@Before
	public void setUp() throws Exception {
	    entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
	}
    @After
    public void tearDown() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
	@Test
	public void testArticle() {
	     entityManager.getTransaction().begin();

	        Article article = new Article();
	        article.setTitre("Title");
	        article.setContenu("Content");
	        article.setDateCreation(LocalDate.now());
	        article.setDatePublication(LocalDate.now());
	        article.setStatut(Status.Publié); 
            Auteur auteur= new Auteur();
            
            auteur.setId(1);
            article.setAuteur(auteur);
	        entityManager.persist(article);
	        entityManager.getTransaction().commit();

	     
	        Article retrievedArticle = entityManager.find(Article.class, article.getId());

	        assertNotNull(retrievedArticle);
	        assertEquals("Test Title", retrievedArticle.getTitre());
	        assertEquals("Test Content", retrievedArticle.getContenu());

	        System.out.println(retrievedArticle);
	}

}
