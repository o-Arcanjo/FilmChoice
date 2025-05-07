package com.filmchoice.util;

import jakarta.persistence.*;

public class MainPaisDeleteAll {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw");
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            tx.begin();

            int deletedCount = em.createQuery("DELETE FROM Pais").executeUpdate();
            System.out.println("Total de registros deletados da tabela País: " + deletedCount);

            tx.commit();
        }
    }
}

