package com.filmchoice.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Local;

import jakarta.persistence.EntityManagerFactory;

@Repository
@Transactional(readOnly = true)
public class LocalDAO extends AbstractDAOImpl<Local, Long> {

    public LocalDAO(EntityManagerFactory emf) {
        super(Local.class, emf);
    }

    public List<Local> getAllLocais() throws PersistenciaDawException {
        try {
            return super.getAll();
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao listar todos os locais", e);
        }
    }
}
