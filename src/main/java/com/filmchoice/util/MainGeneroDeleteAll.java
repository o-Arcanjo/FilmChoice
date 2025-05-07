package com.filmchoice.util;

import jakarta.persistence.*;

public class MainGeneroDeleteAll {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw");
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            tx.begin();

            int deletedCount = em.createQuery("DELETE FROM Genero").executeUpdate();
            System.out.println("Total de registros deletados da tabela Genero: " + deletedCount);

            tx.commit();
        }
    }
}

