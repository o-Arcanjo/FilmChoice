package com.filmchoice.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dao.UsuarioDAO;
import com.filmchoice.entities.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

@Repository  // <- Spring consegue injetar
public class UsuarioDAOImpl extends AbstractDAOImpl<Usuario, Long> implements UsuarioDAO {

    public UsuarioDAOImpl(EntityManagerFactory emf) {
        super(Usuario.class, emf);
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
        super.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) throws PersistenciaDawException {
        return super.update(usuario);
    }

    @Override
    public Usuario getByID(Long id) throws PersistenciaDawException {
        return super.getByID(id);
    }

    @Override
    public List<Usuario> getAll() throws PersistenciaDawException {
        return super.getAll();
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        super.delete(id);
    }
}
