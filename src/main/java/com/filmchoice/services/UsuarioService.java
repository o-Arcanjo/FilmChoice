package com.filmchoice.services;
import com.filmchoice.dto.UsuarioDTO;



public interface UsuarioService{
   void cadastrarUsuario(UsuarioDTO usuarioEntrada) throws ServiceException;
   String login(String email, String senha) throws ServiceException;
   boolean verificarUsuarioCadastrado(String email) throws ServiceException;
}


