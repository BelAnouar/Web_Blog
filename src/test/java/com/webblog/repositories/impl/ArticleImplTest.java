package com.webblog.repositories.impl;

import static org.junit.Assert.*;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webblog.enums.Status;
import com.webblog.models.Article;
import com.webblog.models.Auteur;

public class ArticleImplTest {

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
    public void testSave() {
        entityManager.getTransaction().begin();

        Article article = new Article();
        article.setTitre("Test Title");
        article.setContenu("Test Content");
        article.setDateCreation(LocalDate.now());
        article.setDatePublication(LocalDate.now());
        article.setStatut(Status.Publie);

        Auteur auteur = new Auteur();
        auteur.setId(1);
        article.setAuteur(auteur);

        entityManager.persist(article);
        entityManager.getTransaction().commit();

        Article retrievedArticle = entityManager.find(Article.class, article.getId());

        assertNotNull(retrievedArticle);
        assertEquals("Test Title", retrievedArticle.getTitre());
        assertEquals("Test Content", retrievedArticle.getContenu());
        assertEquals(Status.Publie, retrievedArticle.getStatut());
    }

    @Test
    public void testUpdate() {
        entityManager.getTransaction().begin();
        Article article = new Article();
        article.setTitre("Titre original");
        article.setContenu("Contenu original");
        article.setDateCreation(LocalDate.now());
        article.setDatePublication(LocalDate.now());
        article.setStatut(Status.Brouillon);
        Auteur auteur = new Auteur();
        auteur.setId(1);
        article.setAuteur(auteur);
        entityManager.persist(article);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        Article updatedArticle = entityManager.find(Article.class, article.getId());
        updatedArticle.setTitre("Nouveau titre");
        updatedArticle.setContenu("Nouveau contenu");
        updatedArticle.setStatut(Status.Publie);
      
        entityManager.getTransaction().commit();

       
        Article retrievedArticle = entityManager.find(Article.class, article.getId());
        assertEquals("Nouveau titre", retrievedArticle.getTitre());
        assertEquals("Nouveau contenu", retrievedArticle.getContenu());
        assertEquals(Status.Publie, retrievedArticle.getStatut());
    }

    @Test
    public void testDelete() {
        entityManager.getTransaction().begin();
        Article article = new Article();
        article.setTitre("Test Title");
        article.setContenu("Test Content");
        article.setDateCreation(LocalDate.now());
        article.setDatePublication(LocalDate.now());
        article.setStatut(Status.Publie);

        Auteur auteur = new Auteur();
        auteur.setId(1);
        article.setAuteur(auteur);

        entityManager.persist(article);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        Article toDelete = entityManager.find(Article.class, article.getId());
        entityManager.remove(toDelete);
        entityManager.getTransaction().commit();

        Article deletedArticle = entityManager.find(Article.class, article.getId());
        assertNull(deletedArticle);
    }
}
