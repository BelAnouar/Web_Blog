package com.webblog.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;

import com.webblog.models.Article;
import com.webblog.repositories.GenericRepository;
import com.webblog.repositories.MultiInterface;

import net.bytebuddy.implementation.Implementation;

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
    	//System 
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
            System.out.println("Mise à jour réussie pour l'article : " + existingArticle);
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

    @Override
    public List<Article> getPage(int page, int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    public Article findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Article article = entityManager.find(Article.class, id);
            return article;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

}
