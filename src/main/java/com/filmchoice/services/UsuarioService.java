package com.filmchoice.services;
import com.filmchoice.dto.UsuarioDTO;
import com.filmchoice.dto.UsuarioDTORecebido;



public interface UsuarioService{
   UsuarioDTO cadastrarUsuario(UsuarioDTORecebido usuarioEntrada) throws ServiceException;
   String login(String email, String senha) throws ServiceException;
   boolean verificarUsuarioCadastrado(String email) throws ServiceException;
}


