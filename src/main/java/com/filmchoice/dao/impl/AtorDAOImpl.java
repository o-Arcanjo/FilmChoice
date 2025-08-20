package com.filmchoice.dao.impl;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Ator;
import com.filmchoice.dao.*;
import jakarta.persistence.EntityManagerFactory;


@Repository
@Transactional(readOnly = true)
public class AtorDAOImpl extends AbstractDAOImpl<Ator, Long> implements AtorDAO{

    public AtorDAOImpl(EntityManagerFactory emf) {
        super(Ator.class, emf);
    }

    @Override
    @Transactional
    public void save(Ator ator) throws PersistenciaDawException {
        try {
            super.save(ator);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar Ator", e);
        }
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar Ator", e);
        }
    }
}
