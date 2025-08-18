package com.filmchoice.services;
import java.io.IOException;
import com.filmchoice.dto.UsuarioDTORecebido;




public interface UsuarioService{
   void cadastrarUsuario(UsuarioDTORecebido usuarioEntrada) throws ServiceException, IOException;
   String login(String email, String senha) throws ServiceException;
   boolean verificarUsuarioCadastrado(String email) throws ServiceException;
}


