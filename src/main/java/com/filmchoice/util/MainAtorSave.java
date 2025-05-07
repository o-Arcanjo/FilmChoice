package com.filmchoice.util;

import com.filmchoice.entities.Ator;
import jakarta.persistence.*;

public class MainAtorSave {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw");
             EntityManager em = emf.createEntityManager()) {

            // Criar uma nova instância de Ator
            Ator ator = new Ator("Nome do Ator", java.sql.Date.valueOf("1980-01-01"));

            // Iniciar uma transação
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Salvar a entidade no banco de dados
            em.persist(ator);
            System.out.println("Ator salvo: " + ator);

            // Comitar a transação
            tx.commit();
        }
    }
}