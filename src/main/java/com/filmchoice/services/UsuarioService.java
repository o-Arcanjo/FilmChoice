package com.filmchoice.services;
import java.io.IOException;
import java.util.List;

import com.filmchoice.dto.UsuarioDTO;
import com.filmchoice.dto.UsuarioDTORecebido;
import com.filmchoice.entities.Usuario;


public interface UsuarioService{
   void cadastrarUsuario(UsuarioDTO usuarioEntrada) throws ServiceException, IOException;
   String login(String email, String senha) throws ServiceException;
   boolean verificarUsuarioCadastrado(String email) throws ServiceException;
   //Métodos para CRUD COMPLETO
   Usuario buscarUsuarioPorId(Long id) throws ServiceException;
   Usuario buscarUsuarioPorEmail(String email) throws ServiceException;
   List<Usuario> listarTodosUsuarios() throws ServiceException;
   Usuario atualizarUsuario(Long id, UsuarioDTORecebido usuarioDTO) throws ServiceException;
   void deletarUsuario(Long id) throws ServiceException;
}


