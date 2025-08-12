package com.filmchoice.dao.impl;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Avaliacao;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;

public class AvaliacaoDAO extends AbstractDAOImpl<Avaliacao, Long> {

    public AvaliacaoDAO(Class<Avaliacao> entityClass, EntityManagerFactory emf) {
        super(entityClass, emf);
    }

    @Override
    public void save(Avaliacao avaliacao) throws PersistenciaDawException {
        try {
            super.save(avaliacao);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar Avaliação", e);
        }
    }

    @Override
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
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar Avaliação", e);
        }
    }
}
