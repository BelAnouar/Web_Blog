package com.webblog.repositories.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.webblog.models.Auteur;
import com.webblog.repositories.GenericRepository;
import com.webblog.repositories.MultiInterface;
import com.webblog.utilis.LoggerMessage;

public class AuteurImpl implements GenericRepository<Auteur, Integer>, MultiInterface<Auteur> {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");

    public AuteurImpl() {
        try {
            this.entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
            System.out.println("EntityManagerFactory créé avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de l'EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Boolean save(Auteur entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            LoggerMessage.info("Ajouter l'auteur");

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

    public List<Auteur> getAllAuteur() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Auteur> auteurs = null;
        try {
            String jpql = "SELECT a FROM Auteur a";
            Query query = entityManager.createQuery(jpql, Auteur.class);
            auteurs = query.getResultList();
            LoggerMessage.info("Nombre d'auteurs récupérés: " + (auteurs != null ? auteurs.size() : 0));
        } catch (Exception e) {
            LoggerMessage.error("Erreur lors de la récupération des auteurs: " + e.getMessage());
        } finally {
            entityManager.close();
            LoggerMessage.warn("Fermeture de l'EntityManager");
        }
        return auteurs;
    }
    @Override
    public Boolean update(Auteur entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.merge(entity); 

            transaction.commit();
            LoggerMessage.info("Mise à jour réussie pour l'auteur");
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
            Auteur auteur = entityManager.find(Auteur.class, id);
            if (auteur != null) {
                entityManager.remove(auteur);
                transaction.commit();
                LoggerMessage.info("Auteur supprimé");

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
    public List<Auteur> getPage(int page, int pageSize) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        
        List<Auteur> auteurs = null;
        try {
            transaction.begin();
            
            String jpql = "SELECT a FROM Auteur a";
            Query query = entityManager.createQuery(jpql, Auteur.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            
            auteurs = query.getResultList();
            
            transaction.commit();
            LoggerMessage.info("Liste des auteurs récupérée");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error(e.getMessage());
        } finally {
            entityManager.close();
            LoggerMessage.warn("Close");
        }
        
        return auteurs;
    }

    @Override
    public Integer count() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Integer count = 0;
        try {
            transaction.begin();
            
            String jpql = "SELECT COUNT(a) FROM Auteur a";
            Query query = entityManager.createQuery(jpql, Auteur.class);
            
            count = ((Long) query.getSingleResult()).intValue();
            
            transaction.commit();
            LoggerMessage.info("Nombre total d'auteurs calculé");
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

    public Auteur findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Auteur auteur = entityManager.find(Auteur.class, id);
            return auteur;
        } catch (Exception e) {
            LoggerMessage.error(e.getMessage());
            return null;
        } finally {
            entityManager.close();
            LoggerMessage.warn("Close");
        }
    }
}
