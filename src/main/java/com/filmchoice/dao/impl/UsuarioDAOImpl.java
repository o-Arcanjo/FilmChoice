package com.filmchoice.dao.impl;

import java.util.List;
import java.util.Optional;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Usuario;

import jakarta.persistence.EntityManagerFactory;

import com.filmchoice.dao.UsuarioDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class UsuarioDAOImpl extends AbstractDAOImpl<Usuario, Long> implements UsuarioDAO {

    public UsuarioDAOImpl(Class<Usuario> entityClass, EntityManagerFactory emf) {
        super(entityClass, emf);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) throws PersistenciaDawException {
        try {
            EntityManager em = super.getEntityManager();
            Usuario usuario = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(usuario);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new PersistenciaDawException("Erro ao buscar Usuário por email", e);
        }
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
