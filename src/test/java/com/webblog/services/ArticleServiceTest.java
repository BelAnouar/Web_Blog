package com.webblog.services;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.webblog.enums.Status;
import com.webblog.models.Article;
import com.webblog.models.Auteur;
import com.webblog.repositories.impl.ArticleImpl;

public class ArticleServiceTest {

    @InjectMocks
    private ArticleService articleService;

    @Mock
    private ArticleImpl articleImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Article article = new Article();
        article.setTitre("services1");
        article.setContenu("Test Content");
        article.setDateCreation(LocalDate.now());
        article.setDatePublication(LocalDate.now());
        article.setStatut(Status.Publie);

        Auteur auteur = new Auteur();
        auteur.setId(1);
        article.setAuteur(auteur);

        when(articleImpl.save(article)).thenReturn(true);

        Boolean result = articleService.save(article);

        assertTrue(result);
        verify(articleImpl, times(1)).save(article);
    }

    @Test
    public void testUpdate() {
        Article article = new Article();
        article.setId(21);
        article.setTitre("anwar");
        article.setContenu("Test Content");
        article.setDateCreation(LocalDate.now());
        article.setDatePublication(LocalDate.now());
        article.setStatut(Status.Brouillon);

        when(articleImpl.update(article)).thenReturn(true);

        Boolean result = articleService.update(article);

        assertTrue(result);
        verify(articleImpl, times(1)).update(article);
    }

    @Test
    public void testDelete() {
        int articleId = 20;

        when(articleImpl.delete(articleId)).thenReturn(true);

        Boolean result = articleService.delete(articleId);

        assertTrue(result);
        verify(articleImpl, times(1)).delete(articleId);
    }

    @Test
    public void testFindById() {
        int articleId = 1;
        Article article = new Article();
        article.setId(articleId);

        when(articleImpl.findById(articleId)).thenReturn(article);

        Article result = articleService.findById(articleId);

        assertNotNull(result);
        assertEquals(articleId, result.getId());
        verify(articleImpl, times(1)).findById(articleId);
    }

   
}
