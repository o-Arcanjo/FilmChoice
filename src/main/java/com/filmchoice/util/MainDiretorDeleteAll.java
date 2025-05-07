package com.filmchoice.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MainDiretorDeleteAll {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw");
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            tx.begin();

            int deletedCount = em.createQuery("DELETE FROM Diretor").executeUpdate();
            System.out.println("Total de registros deletados da tabela Diretor: " + deletedCount);

            tx.commit();
        }
    }
}
