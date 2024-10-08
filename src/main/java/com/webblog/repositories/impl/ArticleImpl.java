package com.webblog.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityTransaction;

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

    @Override
    public List<Article> getPage(int page, int pageSize) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        
        List<Article> articles = null;
        
        try {
            transaction.begin();
            
            String jpql = "SELECT a.titre, a.contenu, a.dateCreation, a.datePublication, COUNT(c.id) AS commentaire " +
                          "FROM Article a " +
                          "JOIN Commentaire c ON a.id = c.article.id " +
                          "GROUP BY article.id";
            
            Query query = entityManager.createQuery(jpql,Article.class);
            
            query.setFirstResult((page - 1) * pageSize); 
            query.setMaxResults(pageSize); 
                     
            articles = query.getResultList();
            
            transaction.commit();
            LoggerMessage.info("AFFCIHE DATA l'article ");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error(e.getMessage());
        } finally {
            entityManager.close();
            LoggerMessage.warn("Close");

        }
        
        return articles;
    }

    @Override
    public Integer count() {
    	 EntityManager entityManager = entityManagerFactory.createEntityManager();
    	    EntityTransaction transaction = entityManager.getTransaction();
    	    Integer count = 0;
    	    
    	    try {
    	        transaction.begin();
    	        
    	        String jpql = "SELECT COUNT(a) FROM Article a";
    	        Query query = entityManager.createQuery(jpql,Article.class);
    	        
    	        count = (Integer) query.getSingleResult();
    	        
    	        transaction.commit();
    	    } catch (Exception e) {
    	        if (transaction.isActive()) {
    	            transaction.rollback();
    	        }
                LoggerMessage.error(e.getMessage());
    	    } finally {
    	        entityManager.close();
                LoggerMessage.warn("Close");

    	    }
    	    
    	    return count;
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
