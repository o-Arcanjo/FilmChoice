package com.filmchoice.util;

import java.time.LocalDate;

import com.filmchoice.entities.Diretor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MainDiretorSave {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw");
             EntityManager em = emf.createEntityManager()) {

            // Criar uma nova instância de r
            Diretor diretor = new Diretor("Nome do Diretor", LocalDate.of(1980, 1, 1));
            // Iniciar uma transação
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Salvar a entidade no banco de dados
            em.persist(diretor);
            System.out.println("Diretor salvo: " + diretor);

            // Comitar a transação
            tx.commit();
        }
    }
}