package br.com.selfSystem;

import static org.junit.Assert.*;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

public class EntityManagerTest implements Serializable {

    private static final long serialVersionUID = -2580982842339673122L;

    @Test
    public void entityManagerTest() {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("futurodb").createEntityManager();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
