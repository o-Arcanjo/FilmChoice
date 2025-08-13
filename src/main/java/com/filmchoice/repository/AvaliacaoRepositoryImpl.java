package com.filmchoice.repository;

import com.filmchoice.entities.Avaliacao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Avaliacao avaliacao) {
        em.persist(avaliacao);
    }

    @Override
    public void atualizar(Avaliacao avaliacao) {
        em.merge(avaliacao);
    }

    @Override
    public void deletar(Avaliacao avaliacao) {
        avaliacao = em.merge(avaliacao);
        em.remove(avaliacao);
    }

    @Override
    public Avaliacao buscarPorId(Long id) {
        return em.find(Avaliacao.class, id);
    }

    @Override
    public List<Avaliacao> listarTodos() {
        return em.createQuery("FROM Avaliacao", Avaliacao.class).getResultList();
    }
}
