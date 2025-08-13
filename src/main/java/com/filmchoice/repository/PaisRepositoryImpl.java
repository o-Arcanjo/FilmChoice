package com.filmchoice.repository;

import com.filmchoice.entities.Pais;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class PaisRepositoryImpl implements PaisRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Pais pais) {
        em.persist(pais);
    }

    @Override
    public void atualizar(Pais pais) {
        em.merge(pais);
    }

    @Override
    public void deletar(Pais pais) {
        pais = em.merge(pais);
        em.remove(pais);
    }

    @Override
    public Pais buscarPorId(Long id) {
        return em.find(Pais.class, id);
    }

    @Override
    public List<Pais> listarTodos() {
        return em.createQuery("FROM Pais", Pais.class).getResultList();
    }
}
