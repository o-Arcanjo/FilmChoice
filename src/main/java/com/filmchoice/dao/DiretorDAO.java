package com.filmchoice.dao;

import java.util.List;

import com.filmchoice.entities.Diretor;

public interface DiretorDAO {
    public void save(Diretor diretor) throws PersistenciaDawException;
    public Diretor update(Diretor diretor) throws PersistenciaDawException;
    public Diretor getByID(Long id) throws PersistenciaDawException;
    public List<Diretor> getAll() throws PersistenciaDawException;
    public void delete(Long id) throws PersistenciaDawException;
}
