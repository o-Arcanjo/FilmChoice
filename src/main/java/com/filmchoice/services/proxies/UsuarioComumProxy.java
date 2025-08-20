package com.filmchoice.services.proxies;

import org.springframework.stereotype.Component;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dto.AvaliacaoDTO;
import com.filmchoice.services.AuthService;
import com.filmchoice.services.ServiceException;
import com.filmchoice.services.UsuarioComumService;


@Component
public class UsuarioComumProxy implements UsuarioComumService{

    private final AuthService auth;
    private final UsuarioComumService usuarioComumReal;

    public UsuarioComumProxy(AuthService auth, UsuarioComumService usuarioComumReal){
        this.auth = auth;
        this.usuarioComumReal = usuarioComumReal;
    }

    @Override
    public void fazerAvaliacao(String token, AvaliacaoDTO avaliacao) throws ServiceException, PersistenciaDawException {
      this.auth.autenticar(token);
      this.usuarioComumReal.fazerAvaliacao(token, avaliacao);
    }
    
}
