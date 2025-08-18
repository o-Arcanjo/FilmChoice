package com.filmchoice.services;
import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dao.UsuarioDAO;
import com.filmchoice.dto.Payload;
import com.filmchoice.dto.UsuarioDTO;
import com.filmchoice.dto.UsuarioDTORecebido;
import com.filmchoice.entities.Usuario;
import com.filmchoice.enums.ChaveSecreta;
import com.filmchoice.mapper.impl.UsuarioMapper;

import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.filmchoice.config.LoadPropertiesBd;
import com.filmchoice.enums.CodigoAdmin;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final AuthService authService;
    private final TokenService tokenService;

    public UsuarioServiceImpl(UsuarioDAO usuarioDAO, UsuarioMapper usuarioMapper, AuthService authServiceImpl, TokenService tokenService) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioMapper = usuarioMapper;
        this.authService = authServiceImpl;
        this.tokenService = tokenService;
    }

    @Override
    public boolean verificarUsuarioCadastrado(String email) throws ServiceException{
        try {
            return usuarioDAO.buscarPorEmail(email).isPresent();
        } catch (PersistenciaDawException e) {
            throw new ServiceException("Erro ao verificar usuário cadastrado", e);
        }
    }

    @Override
    public void cadastrarUsuario(UsuarioDTORecebido usuarioDTORecebido) throws ServiceException, IOException{
        if (verificarUsuarioCadastrado(usuarioDTORecebido.getEmail())) {
            throw new ServiceException("Usuário já cadastrado com o e-mail: " + usuarioDTORecebido.getEmail());
        }
        Properties props = LoadPropertiesBd.loadProperties(CodigoAdmin.Codigo); 
        String codigoSecreto = props.getProperty("CODIGO_ADMIN");
        if(usuarioDTORecebido.getSenha() != null && !usuarioDTORecebido.getSenha().equals(codigoSecreto)){

        }

        String senhaHash = authService.criptografarSenha(usuarioDTORecebido.getSenha());

    UsuarioDTO usuarioDTO = UsuarioDTO.builder()
            .nome(usuarioDTORecebido.getNome())
            .email(usuarioDTORecebido.getEmail())
            .senha(senhaHash)
            .papel(usuarioDTORecebido.getPapel())
            .build();

        try {   
            Usuario usuario = usuarioMapper.converterElementoEntidade(usuarioDTO);
            usuarioDAO.save(usuario);
        } catch (PersistenciaDawException e) {
            throw new ServiceException("Erro ao cadastrar usuário", e);
        }
    }

    @Override
public String login(String email, String senha) throws ServiceException {
    try {
        Usuario usuario = usuarioDAO.buscarPorEmail(email)
            .orElseThrow(() -> new PersistenciaDawException("Credenciais inválidas"));

        boolean senhaCorreta = authService.verificarSenha(senha, usuario.getSenha());

        if (!verificarUsuarioCadastrado(email) || !senhaCorreta) {
            throw new ServiceException("Credenciais inválidas");
        }

    Payload payload = Payload.builder() 
                             .userId(usuario.getId())
                             .papel(usuario.getPapel())
                             .build();       
                             
    return tokenService.criarToken(payload, ChaveSecreta.TOKEN_JWT);

    } catch (PersistenciaDawException e) {
        throw new ServiceException("Erro ao autenticar usuário: " + e.getMessage(), e);
    } catch (ServiceException e) {
        throw e;
    }
}


}