package com.filmchoice.dao;

import java.util.List;

import com.filmchoice.entities.Idioma;

public interface IdiomaDAO {
    public void save(Idioma idioma) throws PersistenciaDawException;
    public Idioma update(Idioma idioma) throws PersistenciaDawException;
    public Idioma getByID(Long id) throws PersistenciaDawException;
    public List<Idioma> getAll() throws PersistenciaDawException;
    public void delete(Long id) throws PersistenciaDawException;
}
