package com.webblog.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import com.webblog.models.Article;
import com.webblog.repositories.GenericRepository;
import com.webblog.repositories.MultiInterface;
import com.webblog.utilis.LoggerMessage;


public class ArticleImpl implements GenericRepository<Article, Integer>, MultiInterface<Article> {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("webblogPU");

    public ArticleImpl() {
    }

    @Override
    public Boolean save(Article entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            LoggerMessage.info("Ajouter l'article   ");

            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error(e.getMessage());
            return false;
        } finally {
            entityManager.close();
            LoggerMessage.warn("Close");

        }
    }

    @Override
    public Boolean update(Article entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            
            Article existingArticle = entityManager.find(Article.class, entity);
          
             System.out.println(existingArticle);
            existingArticle.setTitre(entity.getTitre());
            existingArticle.setContenu(entity.getContenu());
            existingArticle.setDateCreation(entity.getDateCreation());
            existingArticle.setDatePublication(entity.getDatePublication());
            existingArticle.setStatut(entity.getStatut());

            entityManager.merge(existingArticle);
            transaction.commit();
            LoggerMessage.info("Mise à jour réussie pour l'article ");

            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error(e.getMessage());
            return false;
        } finally {
            entityManager.close();
            LoggerMessage.warn("Close");

        }
    }

    @Override
    public Boolean delete(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Article article = entityManager.find(Article.class, id);
            if (article != null) {
                entityManager.remove(article);
                transaction.commit();
                LoggerMessage.info("Delete Atricle");

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error(e.getMessage());
            return false;
        } finally {
            entityManager.close();
            LoggerMessage.warn("Close");

            
        }
    }

    public Article findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Article article = entityManager.find(Article.class, id);
            return article;
        } catch (Exception e) {
            LoggerMessage.error(e.getMessage());
            return null;
        } finally {
            entityManager.close();
            LoggerMessage.warn("Close");

        }
    }

}
