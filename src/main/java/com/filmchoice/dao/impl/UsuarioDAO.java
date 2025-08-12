package com.filmchoice.dao.impl;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Usuario;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;

public class UsuarioDAO extends AbstractDAOImpl<Usuario, Long> {

    public UsuarioDAO(Class<Usuario> entityClass, EntityManagerFactory emf) {
        super(entityClass, emf);
    }

    @Override
    public void save(Usuario usuario) throws PersistenciaDawException {
        try {
            super.save(usuario);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar Usuário", e);
        }
    }

    @Override
    public Usuario update(Usuario usuario) throws PersistenciaDawException {
        try {
            return super.update(usuario);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao atualizar Usuário", e);
        }
    }

    @Override
    public Usuario getByID(Long id) throws PersistenciaDawException {
        try {
            return super.getByID(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao buscar Usuário por ID", e);
        }
    }

    @Override
    public List<Usuario> getAll() throws PersistenciaDawException {
        try {
            return super.getAll();
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao listar todos os Usuários", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar Usuário", e);
        }
    }
}
