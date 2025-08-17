package com.filmchoice.dao.impl;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Filme;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManagerFactory;

@Repository
@Transactional(readOnly = true)
public class FilmeDAO extends AbstractDAOImpl<Filme, Long> {

    public FilmeDAO(EntityManagerFactory emf) {
        super(Filme.class, emf);
    }

    @Override
    @Transactional
    public void save(Filme filme) throws PersistenciaDawException {
        try {
            super.save(filme);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar Filme", e);
        }
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar Filme", e);
        }
    }
}
