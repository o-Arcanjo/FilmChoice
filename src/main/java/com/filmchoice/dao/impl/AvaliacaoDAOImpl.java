package com.filmchoice.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.filmchoice.dao.AvaliacaoDAO;
import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Avaliacao;

import jakarta.persistence.EntityManagerFactory;

@Repository
@Transactional(readOnly = true)
public class AvaliacaoDAOImpl extends AbstractDAOImpl<Avaliacao, Long> implements AvaliacaoDAO{

    public AvaliacaoDAOImpl(EntityManagerFactory emf) {
        super(Avaliacao.class, emf);
    }

    @Override
    @Transactional
    public void save(Avaliacao avaliacao) throws PersistenciaDawException {
        try {
            super.save(avaliacao);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar Avaliação", e);
        }
    }

    @Override
    @Transactional
    public Avaliacao update(Avaliacao avaliacao) throws PersistenciaDawException {
        try {
            return super.update(avaliacao);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao atualizar Avaliação", e);
        }
    }

    @Override
    public Avaliacao getByID(Long id) throws PersistenciaDawException {
        try {
            return super.getByID(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao buscar Avaliação por ID", e);
        }
    }

    @Override
    public List<Avaliacao> getAll() throws PersistenciaDawException {
        try {
            return super.getAll();
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao listar todas as Avaliações", e);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar Avaliação", e);
        }
    }
}
