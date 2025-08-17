package com.filmchoice.dao.impl;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dao.UsuarioDAO;
import com.filmchoice.entities.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class UsuarioDAOImpl extends AbstractDAOImpl<Usuario, Long> implements UsuarioDAO {

    public UsuarioDAOImpl(EntityManagerFactory emf) {
        super(Usuario.class, emf);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) throws PersistenciaDawException {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
            query.setParameter("email", email);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new PersistenciaDawException("Erro ao buscar Usuário por email", e);
        }
    }

    @Override
    @Transactional
    public void save(Usuario usuario) throws PersistenciaDawException {
        try {
            super.save(usuario);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao salvar Usuário", e);
        }
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(Long id) throws PersistenciaDawException {
        try {
            super.delete(id);
        } catch (PersistenciaDawException e) {
            throw new PersistenciaDawException("Erro ao deletar Usuário", e);
        }
    }
}
