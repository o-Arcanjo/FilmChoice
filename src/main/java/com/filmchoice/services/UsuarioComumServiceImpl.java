package com.filmchoice.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dto.AvaliacaoDTO;
import com.filmchoice.dto.FilmeDTO;
import com.filmchoice.dto.Payload;
import com.filmchoice.dto.UsuarioDTO;
import com.filmchoice.enums.ChaveSecreta;


@Service("usuarioComumReal")
public class UsuarioComumServiceImpl implements UsuarioComumService {

    private final TokenService tokenService;
    private final AvaliacaoService avaliacaoService;

    public UsuarioComumServiceImpl(TokenService tokenService,
     @Qualifier("avaliacaoServiceImpl") AvaliacaoService avaliacaoService){
        this.tokenService = tokenService;
        this.avaliacaoService = avaliacaoService;
    }

    @Override
    public void fazerAvaliacao(String token, AvaliacaoDTO avaliacao) throws ServiceException, PersistenciaDawException {
        Payload payload = tokenService.validarToken(token, ChaveSecreta.TOKEN_JWT);
        Long userId = payload.getUserId();

        AvaliacaoDTO avaliacaoUsuario = AvaliacaoDTO.builder()
                .filme(FilmeDTO.builder().id(avaliacao.getFilme().getId()).build())
                .usuario(UsuarioDTO.builder().id(userId).build())
                .nota(avaliacao.getNota())
                .comentario(avaliacao.getComentario())
                .build();

        this.avaliacaoService.fazerAvaliacao(avaliacaoUsuario);
    }
}
