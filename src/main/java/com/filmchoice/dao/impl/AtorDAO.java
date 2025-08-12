package com.filmchoice.dao.impl;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Ator;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;

public class AtorDAO extends AbstractDAOImpl<Ator, Long> {

    public AtorDAO(Class<Ator> entityClass, EntityManagerFactory emf) {
        super(entityClass, emf);
    }
    @Override
    public void save(Ator ator) throws PersistenciaDawException {
        try {
            super.save(ator);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar Ator", e);
        }
    }

    @Override
    public Ator update(Ator ator) throws PersistenciaDawException {
        try {
            return super.update(ator);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao atualizar Ator", e);
        }
    }

    @Override
    public Ator getByID(Long id) throws PersistenciaDawException {
        try {
            return super.getByID(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao buscar Ator por ID", e);
        }
    }

    @Override
    public List<Ator> getAll() throws PersistenciaDawException {
        try {
            return super.getAll();
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao listar todos os Atores", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar Ator", e);
        }
    }
}
