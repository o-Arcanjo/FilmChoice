package com.filmchoice.util;

import java.time.LocalDate;

import com.filmchoice.entities.Usuario;
import com.filmchoice.enums.Papel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MainUsuarioSave {
     public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw");
             EntityManager em = emf.createEntityManager()) {

            // Criar uma nova instância de Usuario
            Usuario usuario = new Usuario("Miguel Arcajo", "CXXgI5bHKkZfzjLoKtNl3sdR5MtcW8lzAa5mEzkb3bY=", "oarcanjovalentim@gmail.com", Papel.USER);

            // Iniciar uma transação
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Salvar a entidade no banco de dados
            em.persist(usuario);
            System.out.println("Usuario salvo: " + usuario);

            // Comitar a transação
            tx.commit();
        }
    }
}
