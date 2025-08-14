package com.filmchoice.dao;

import java.util.List;

import com.filmchoice.entities.Ator;

public interface AtorDAO {
    public void save(Ator ator) throws PersistenciaDawException;
    public Ator update(Ator ator) throws PersistenciaDawException;
    public Ator getByID(Long id) throws PersistenciaDawException;
    public List<Ator> getAll() throws PersistenciaDawException;
    public void delete(Long id) throws PersistenciaDawException;
}
