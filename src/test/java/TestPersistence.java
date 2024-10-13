

import static org.junit.Assert.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;


public class TestPersistence {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("webblogPU");
        System.out.println("EntityManagerFactory created: " + (emf != null));
        emf.close();
    }
}