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
            System.out.println("Mise à jour réussie pour l'article  : " + existingArticle);
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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        
        List<Article> articles = null;
        
        try {
            transaction.begin();
            
            String jpql = "SELECT a.titre, a.contenu, a.dateCreation, a.datePublication, COUNT(c.id) AS commentaire " +
                          "FROM Article a " +
                          "JOIN Commentaire c ON a.id = c.article.id " +
                          "GROUP BY article.id";
            
            Query query = entityManager.createQuery(jpql);
            
            query.setFirstResult((page - 1) * pageSize); 
            query.setMaxResults(pageSize); 
                     
            articles = query.getResultList();
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        
        return articles;
    }

    @Override
    public long count() {
    	 EntityManager entityManager = entityManagerFactory.createEntityManager();
    	    EntityTransaction transaction = entityManager.getTransaction();
    	    long count = 0;
    	    
    	    try {
    	        transaction.begin();
    	        
    	        String jpql = "SELECT COUNT(a) FROM Article a";
    	        Query query = entityManager.createQuery(jpql);
    	        
    	        count = (Long) query.getSingleResult();
    	        
    	        transaction.commit();
    	    } catch (Exception e) {
    	        if (transaction.isActive()) {
    	            transaction.rollback();
    	        }
    	        e.printStackTrace();
    	    } finally {
    	        entityManager.close();
    	    }
    	    
    	    return count;
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
