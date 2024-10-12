package com.webblog.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.webblog.models.Article;
import com.webblog.repositories.GenericRepository;
import com.webblog.repositories.MultiInterface;
import com.webblog.utilis.LoggerMessage;

public class ArticleImpl implements GenericRepository<Article, Integer>, MultiInterface<Article> {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("webblogPU");

    
    public ArticleImpl() {
    	   try {
               this.entityManagerFactory = Persistence.createEntityManagerFactory("webblogPU");
               System.out.println("EntityManagerFactory créé avec succès");
           } catch (Exception e) {
               System.err.println("Erreur lors de la création de l'EntityManagerFactory: " + e.getMessage());
               e.printStackTrace();
           }
    }

    @Override
    public Boolean save(Article entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            LoggerMessage.info("Ajouter l'article");

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
            Article existingArticle = entityManager.find(Article.class, entity.getId());

            if (existingArticle != null) {
                existingArticle.setTitre(entity.getTitre());
                existingArticle.setContenu(entity.getContenu());
                existingArticle.setDateCreation(entity.getDateCreation());
                existingArticle.setDatePublication(entity.getDatePublication());
                existingArticle.setStatut(entity.getStatut());

                entityManager.merge(existingArticle);
                transaction.commit();
                LoggerMessage.info("Mise à jour réussie pour l'article");
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
    public Boolean delete(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Article article = entityManager.find(Article.class, id);
            if (article != null) {
                entityManager.remove(article);
                transaction.commit();
                LoggerMessage.info("Delete Article");
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
    @SuppressWarnings("unchecked")
    public List<Article> getPage(int page, int pageSize) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Article> articles = null;
        try {
        	String jpql = "SELECT a.titre, a.contenu, a.dateCreation, a.datePublication, COUNT(c.id) AS commentaire " +
                    "FROM Article a " +
                    "JOIN a.commentaires c " +
                    "GROUP BY a.id";


            Query query = entityManager.createQuery(jpql);
            query.setFirstResult((page - 1) * pageSize); 
            query.setMaxResults(pageSize); 

            
            articles = query.getResultList(); 
            LoggerMessage.info("Affichage des articles");
        } catch (Exception e) {
            LoggerMessage.error("Erreur lors de la récupération des articles: " + e.getMessage());
        } finally {
            entityManager.close();
            LoggerMessage.warn("Fermeture de l'EntityManager");
        }
        return articles; 
    }



    @Override
    public Integer count() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT COUNT(a) FROM Article a"; 
            Query query = entityManager.createQuery(jpql); 

            Long result = (Long) query.getSingleResult(); 
            return result.intValue(); 
        } catch (Exception e) {
            LoggerMessage.error("Erreur lors du comptage des articles: " + e.getMessage());
            return 0; 
        } finally {
            entityManager.close(); 
            LoggerMessage.warn("Fermeture de l'EntityManager");
        }
    }


    public Article findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Article article = entityManager.find(Article.class, id);
            if (article == null) {
                LoggerMessage.warn("Article with ID " + id + " not found.");
            }
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
