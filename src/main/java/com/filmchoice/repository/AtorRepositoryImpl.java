package com.filmchoice.repository;

import com.filmchoice.entities.Ator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class AtorRepositoryImpl implements AtorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Ator ator) {
        em.persist(ator);
    }

    @Override
    public void atualizar(Ator ator) {
        em.merge(ator);
    }

    @Override
    public void deletar(Ator ator) {
        ator = em.merge(ator);
        em.remove(ator);
    }

    @Override
    public Ator buscarPorId(Long id) {
        return em.find(Ator.class, id);
    }

    @Override
    public List<Ator> listarTodos() {
        return em.createQuery("FROM Ator", Ator.class).getResultList();
    }
}
