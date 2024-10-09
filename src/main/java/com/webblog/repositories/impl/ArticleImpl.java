package com.webblog.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;

import com.webblog.models.Article;
import com.webblog.repositories.GenericRepository;

public class ArticleImpl implements GenericRepository<Article, Integer> {
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
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Boolean update(Article entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Article existingArticle = entityManager.find(Article.class, entity.getId());
            if (existingArticle == null) {
                System.out.println("No article found with ID: " + entity.getId());
                transaction.rollback();
                return false;
            }

            System.out.println("Before merge: " + existingArticle);
            entityManager.merge(entity);
            entityManager.flush(); // Ensure changes are flushed to the database
            transaction.commit();
            System.out.println("Update successful for article: " + entity);
            return true;

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
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
                return true;
            } else {
                return false; 
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }
}
