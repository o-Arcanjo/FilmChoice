package com.filmchoice.repository;

import com.filmchoice.entities.Genero;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class GeneroRepositoryImpl implements GeneroRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Genero genero) {
        em.persist(genero);
    }

    @Override
    public void atualizar(Genero genero) {
        em.merge(genero);
    }

    @Override
    public void deletar(Genero genero) {
        genero = em.merge(genero);
        em.remove(genero);
    }

    @Override
    public Genero buscarPorId(Long id) {
        return em.find(Genero.class, id);
    }

    @Override
    public List<Genero> listarTodos() {
        return em.createQuery("FROM Genero", Genero.class).getResultList();
    }
}
