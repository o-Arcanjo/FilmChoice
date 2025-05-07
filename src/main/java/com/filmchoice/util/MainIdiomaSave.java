package com.filmchoice.util;

import com.filmchoice.entities.Idioma;
import jakarta.persistence.*;

public class MainIdiomaSave {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw");
             EntityManager em = emf.createEntityManager()) {

            // Criar uma nova instância de Idioma
            Idioma idioma = new Idioma("Português");

            // Iniciar uma transação
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Salvar a entidade no banco de dados
            em.persist(idioma);
            System.out.println("Idioma salvo: " + idioma);

            // Comitar a transação
            tx.commit();
        }
    }
}
