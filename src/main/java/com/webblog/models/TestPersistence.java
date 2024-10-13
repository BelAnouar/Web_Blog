package com.webblog.models;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestPersistence {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Af");
            System.out.println("EntityManagerFactory created: " + (emf != null));
            emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}