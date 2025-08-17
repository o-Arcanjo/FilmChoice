package com.filmchoice.services;
import com.filmchoice.dto.UsuarioDTORecebido;
import com.filmchoice.entities.Usuario;




public interface UsuarioService{
   Usuario cadastrarUsuario(UsuarioDTORecebido usuarioEntrada) throws ServiceException;
   String login(String email, String senha) throws ServiceException;
   boolean verificarUsuarioCadastrado(String email) throws ServiceException;
}


