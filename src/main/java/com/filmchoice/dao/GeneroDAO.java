package com.filmchoice.dao;

import java.util.List;

import com.filmchoice.entities.Genero;

public interface GeneroDAO {
    public void save(Genero genero) throws PersistenciaDawException;
    public Genero update(Genero genero) throws PersistenciaDawException;
    public Genero getByID(Long id) throws PersistenciaDawException;
    public List<Genero> getAll() throws PersistenciaDawException;
    public void delete(Long id) throws PersistenciaDawException;
}
