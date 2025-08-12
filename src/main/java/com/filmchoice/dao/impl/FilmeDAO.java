package com.filmchoice.dao.impl;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Filme;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;

public class FilmeDAO extends AbstractDAOImpl<Filme, Long> {

    public FilmeDAO(Class<Filme> entityClass, EntityManagerFactory emf) {
        super(entityClass, emf);
    }

    @Override
    public void save(Filme filme) throws PersistenciaDawException {
        try {
            super.save(filme);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar Filme", e);
        }
    }

    @Override
    public Filme update(Filme filme) throws PersistenciaDawException {
        try {
            return super.update(filme);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao atualizar Filme", e);
        }
    }

    @Override
    public Filme getByID(Long id) throws PersistenciaDawException {
        try {
            return super.getByID(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao buscar Filme por ID", e);
        }
    }

    @Override
    public List<Filme> getAll() throws PersistenciaDawException {
        try {
            return super.getAll();
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao listar todos os Filmes", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar Filme", e);
        }
    }
}
