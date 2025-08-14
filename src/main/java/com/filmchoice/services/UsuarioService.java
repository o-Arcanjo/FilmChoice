package com.filmchoice.services;
import com.filmchoice.dto.UsuarioDTO;

public interface UsuarioService{
   void cadastrarUsuario(UsuarioDTO usuarioEntrada) throws ServiceException;
   boolean login(String email, String senha) throws ServiceException;
   boolean verificarUsuarioCadastrado(UsuarioDTO usuarioEntrada) throws ServiceException;
}


