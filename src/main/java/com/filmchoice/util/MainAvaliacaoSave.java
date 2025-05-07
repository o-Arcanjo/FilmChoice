package com.filmchoice.util;

import java.time.LocalDate;

import com.filmchoice.entities.Avaliacao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MainAvaliacaoSave {
     public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw");
             EntityManager em = emf.createEntityManager()) {

            // Criar uma nova instância de Avaliacao
            Avaliacao avaliacao = new Avaliacao(5, "Muito Bom");
            // Iniciar uma transação
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Salvar a entidade no banco de dados
            em.persist(avaliacao);
            System.out.println("Avaliacao salvo: " + avaliacao);

            // Comitar a transação
            tx.commit();
        }
    }
}
