package com.filmchoice.dao;

import java.util.List;

import com.filmchoice.entities.Filme;

public interface FilmeDAO {
    public void save(Filme filme) throws PersistenciaDawException;
    public Filme update(Filme filme) throws PersistenciaDawException;
    public Filme getByID(Long id) throws PersistenciaDawException;
    public List<Filme> getAll() throws PersistenciaDawException;
    public void delete(Long id) throws PersistenciaDawException;
}
