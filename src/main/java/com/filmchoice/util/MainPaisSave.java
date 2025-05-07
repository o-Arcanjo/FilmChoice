package com.filmchoice.util;

import com.filmchoice.entities.Pais;
import jakarta.persistence.*;

public class MainPaisSave {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw");
             EntityManager em = emf.createEntityManager()) {

            // Criar uma nova instância de Pais
            Pais pais = new Pais("Brasil", "BR");

            // Iniciar uma transação
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Salvar a entidade no banco de dados
            em.persist(pais);
            System.out.println("Pais salvo: " + pais);

            // Comitar a transação
            tx.commit();
        }
    }
}

