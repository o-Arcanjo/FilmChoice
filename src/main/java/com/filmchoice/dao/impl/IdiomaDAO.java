package com.filmchoice.dao.impl;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Idioma;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;

public class IdiomaDAO extends AbstractDAOImpl<Idioma, Long> {

    public IdiomaDAO(Class<Idioma> entityClass, EntityManagerFactory emf) {
        super(entityClass, emf);
    }

    @Override
    public void save(Idioma idioma) throws PersistenciaDawException {
        try {
            super.save(idioma);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar Idioma", e);
        }
    }

    @Override
    public Idioma update(Idioma idioma) throws PersistenciaDawException {
        try {
            return super.update(idioma);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao atualizar Idioma", e);
        }
    }

    @Override
    public Idioma getByID(Long id) throws PersistenciaDawException {
        try {
            return super.getByID(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao buscar Idioma por ID", e);
        }
    }

    @Override
    public List<Idioma> getAll() throws PersistenciaDawException {
        try {
            return super.getAll();
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao listar todos os Idiomas", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar Idioma", e);
        }
    }
}
