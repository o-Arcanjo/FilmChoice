package com.filmchoice.services;
import com.filmchoice.dto.UsuarioDTORecebido;




public interface UsuarioService{
   void cadastrarUsuario(UsuarioDTORecebido usuarioEntrada) throws ServiceException;
   String login(String email, String senha) throws ServiceException;
   boolean verificarUsuarioCadastrado(String email) throws ServiceException;
}


