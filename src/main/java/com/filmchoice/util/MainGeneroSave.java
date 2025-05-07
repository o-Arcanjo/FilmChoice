package com.filmchoice.util;

import com.filmchoice.entities.Genero;
import jakarta.persistence.*;

public class MainGeneroSave {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw");
             EntityManager em = emf.createEntityManager()) {

            // Criar uma nova instância de Genero
            Genero genero = new Genero("Ação");

            // Iniciar uma transação
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Salvar a entidade no banco de dados
            em.persist(genero);
            System.out.println("Genero salvo: " + genero);

            // Comitar a transação
            tx.commit();
        }
    }
}
