
package com.filmchoice.dao;
import java.util.List;
import java.util.Optional;

import com.filmchoice.entities.Ator;
import com.filmchoice.entities.Usuario;

public interface UsuarioDAO{
    Optional<Usuario> buscarPorEmail(String email) throws PersistenciaDawException;
    public Usuario save(Usuario usuario) throws PersistenciaDawException;
    public Usuario update(Usuario usuario) throws PersistenciaDawException;
    public Ator getByID(Long id) throws PersistenciaDawException;
    public List<Usuario> getAll() throws PersistenciaDawException;
    public void delete(Long id) throws PersistenciaDawException;
}
