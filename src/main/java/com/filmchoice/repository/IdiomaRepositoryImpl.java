package com.filmchoice.repository;

import com.filmchoice.entities.Idioma;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class IdiomaRepositoryImpl implements IdiomaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Idioma idioma) {
        em.persist(idioma);
    }

    @Override
    public void atualizar(Idioma idioma) {
        em.merge(idioma);
    }

    @Override
    public void deletar(Idioma idioma) {
        idioma = em.merge(idioma);
        em.remove(idioma);
    }

    @Override
    public Idioma buscarPorId(Long id) {
        return em.find(Idioma.class, id);
    }

    @Override
    public List<Idioma> listarTodos() {
        return em.createQuery("FROM Idioma", Idioma.class).getResultList();
    }
}
