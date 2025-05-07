package com.filmchoice.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.filmchoice.entities.Filme;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MainFilmeSave {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw");
             EntityManager em = emf.createEntityManager()) {

            // Criar uma nova instância de Filme
            Filme filme = new Filme("Conclave", LocalDateTime.of(2025,1, 23, 18,30), 120, new BigDecimal(1_000_000_000.00));
            // Iniciar uma transação
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Salvar a entidade no banco de dados
            em.persist(filme);
            System.out.println("Filme salvo: " + filme);

            // Comitar a transação
            tx.commit();
        }
    }
}




   
