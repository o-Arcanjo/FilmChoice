package com.filmchoice.repository;

import com.filmchoice.entities.Diretor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class DiretorRepositoryImpl implements DiretorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Diretor diretor) {
        em.persist(diretor);
    }

    @Override
    public void atualizar(Diretor diretor) {
        em.merge(diretor);
    }

    @Override
    public void deletar(Diretor diretor) {
        diretor = em.merge(diretor);
        em.remove(diretor);
    }

    @Override
    public Diretor buscarPorId(Long id) {
        return em.find(Diretor.class, id);
    }

    @Override
    public List<Diretor> listarTodos() {
        return em.createQuery("FROM Diretor", Diretor.class).getResultList();
    }
}
