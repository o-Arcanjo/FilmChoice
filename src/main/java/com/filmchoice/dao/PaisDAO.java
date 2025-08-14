package com.filmchoice.dao;

import java.util.List;

import com.filmchoice.entities.Pais;

public interface PaisDAO {
    public void save(Pais pais) throws PersistenciaDawException;
    public Pais update(Pais pais) throws PersistenciaDawException;
    public Pais getByID(Long id) throws PersistenciaDawException;
    public List<Pais> getAll() throws PersistenciaDawException;
    public void delete(Long id) throws PersistenciaDawException;
}
