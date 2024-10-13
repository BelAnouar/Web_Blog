package com.webblog.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.webblog.models.Commontaire;
import com.webblog.repositories.GenericRepository;
import com.webblog.utilis.LoggerMessage;

public class CommantaireImpl implements GenericRepository<Commontaire, Integer> {

    private EntityManagerFactory entityManagerFactory;

    
    public CommantaireImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

   
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Boolean save(Commontaire entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            LoggerMessage.info("Ajouter le commentaire");
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
    public Boolean update(Commontaire entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Commontaire existingComment = entityManager.find(Commontaire.class, entity.getId());
            if (existingComment == null) {
                return false;
            }
            existingComment.setContenu(entity.getContenu());
            existingComment.setDateCreation(entity.getDateCreation());
            existingComment.setStatus(entity.getStatus());
            entityManager.merge(existingComment);
            transaction.commit();
            LoggerMessage.info("Mise à jour réussie pour le commentaire");
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
            Commontaire commentaire = entityManager.find(Commontaire.class, id);
            if (commentaire != null) {
                entityManager.remove(commentaire);
                transaction.commit();
                LoggerMessage.info("Commentaire supprimé");
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
}
