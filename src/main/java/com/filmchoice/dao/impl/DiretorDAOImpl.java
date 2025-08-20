package com.filmchoice.dao.impl;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Diretor;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManagerFactory;
import com.filmchoice.dao.*;
@Repository
@Transactional(readOnly = true)
public class DiretorDAOImpl extends AbstractDAOImpl<Diretor, Long> implements DiretorDAO{

    public DiretorDAOImpl(EntityManagerFactory emf) {
        super(Diretor.class, emf);
    }

    @Override
    @Transactional
    public void save(Diretor diretor) throws PersistenciaDawException {
        try {
            super.save(diretor);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar Diretor", e);
        }
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar Diretor", e);
        }
    }
}
