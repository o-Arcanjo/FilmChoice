package com.filmchoice.dao;
import java.util.List;

import com.filmchoice.entities.Avaliacao;

public interface AvaliacaoDAO {
    public void save(Avaliacao avaliacao) throws PersistenciaDawException;
    public Avaliacao update(Avaliacao avaliacao) throws PersistenciaDawException;
    public Avaliacao getByID(Long id) throws PersistenciaDawException;
    public List<Avaliacao> getAll() throws PersistenciaDawException;
    public void delete(Long id) throws PersistenciaDawException;
}




