package com.filmchoice.dao.impl;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Diretor;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;

public class DiretorDAO extends AbstractDAOImpl<Diretor, Long> {

    public DiretorDAO(Class<Diretor> entityClass, EntityManagerFactory emf) {
        super(entityClass, emf);
    }

    @Override
    public void save(Diretor diretor) throws PersistenciaDawException {
        try {
            super.save(diretor);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar Diretor", e);
        }
    }

    @Override
    public Diretor update(Diretor diretor) throws PersistenciaDawException {
        try {
            return super.update(diretor);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao atualizar Diretor", e);
        }
    }

    @Override
    public Diretor getByID(Long id) throws PersistenciaDawException {
        try {
            return super.getByID(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao buscar Diretor por ID", e);
        }
    }

    @Override
    public List<Diretor> getAll() throws PersistenciaDawException {
        try {
            return super.getAll();
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao listar todos os Diretores", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar Diretor", e);
        }
    }
}
