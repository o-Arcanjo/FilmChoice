package com.filmchoice.dao.impl;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Pais;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManagerFactory;

import org.springframework.transaction.annotation.Transactional;

import com.filmchoice.dao.PaisDAO;



@Repository
@Transactional(readOnly = true)
public class PaisDAOImpl extends AbstractDAOImpl<Pais, Long> implements PaisDAO{

    public PaisDAOImpl( EntityManagerFactory emf) {
        super(Pais.class, emf);
    }


    @Override
    @Transactional
    public void save(Pais pais) throws PersistenciaDawException {
        try {
            super.save(pais);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar País", e);
        }
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar País", e);
        }
    }
}
