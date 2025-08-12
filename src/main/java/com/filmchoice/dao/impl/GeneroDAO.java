package com.filmchoice.dao.impl;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Genero;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;

public class GeneroDAO extends AbstractDAOImpl<Genero, Long> {

    public GeneroDAO(Class<Genero> entityClass, EntityManagerFactory emf) {
        super(entityClass, emf);
    }

    @Override
    public void save(Genero genero) throws PersistenciaDawException {
        try {
            super.save(genero);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar Gênero", e);
        }
    }

    @Override
    public Genero update(Genero genero) throws PersistenciaDawException {
        try {
            return super.update(genero);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao atualizar Gênero", e);
        }
    }

    @Override
    public Genero getByID(Long id) throws PersistenciaDawException {
        try {
            return super.getByID(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao buscar Gênero por ID", e);
        }
    }

    @Override
    public List<Genero> getAll() throws PersistenciaDawException {
        try {
            return super.getAll();
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao listar todos os Gêneros", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar Gênero", e);
        }
    }
}
