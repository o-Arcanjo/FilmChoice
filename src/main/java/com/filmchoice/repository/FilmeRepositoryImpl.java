package com.filmchoice.repository;

import com.filmchoice.entities.Filme;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class FilmeRepositoryImpl implements FilmeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Filme filme) {
        em.persist(filme);
    }

    @Override
    public void atualizar(Filme filme) {
        em.merge(filme);
    }

    @Override
    public void deletar(Filme filme) {
        filme = em.merge(filme);
        em.remove(filme);
    }

    @Override
    public Filme buscarPorId(Long id) {
        return em.find(Filme.class, id);
    }

    @Override
    public List<Filme> listarTodos() {
        return em.createQuery("FROM Filme", Filme.class).getResultList();
    }
}
