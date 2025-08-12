package com.filmchoice.dao.impl;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Pais;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;

public class PaisDAO extends AbstractDAOImpl<Pais, Long> {

    public PaisDAO(Class<Pais> entityClass, EntityManagerFactory emf) {
        super(entityClass, emf);
    }

    @Override
    public void save(Pais pais) throws PersistenciaDawException {
        try {
            super.save(pais);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar País", e);
        }
    }

    @Override
    public Pais update(Pais pais) throws PersistenciaDawException {
        try {
            return super.update(pais);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao atualizar País", e);
        }
    }

    @Override
    public Pais getByID(Long id) throws PersistenciaDawException {
        try {
            return super.getByID(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao buscar País por ID", e);
        }
    }

    @Override
    public List<Pais> getAll() throws PersistenciaDawException {
        try {
            return super.getAll();
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao listar todos os Países", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar País", e);
        }
    }
}
