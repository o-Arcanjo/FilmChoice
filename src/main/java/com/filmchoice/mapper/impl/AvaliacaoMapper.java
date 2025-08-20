package com.filmchoice.mapper.impl;

import com.filmchoice.dto.AvaliacaoDTO;
import com.filmchoice.entities.Avaliacao;
import com.filmchoice.entities.Filme;
import com.filmchoice.entities.Usuario;
import com.filmchoice.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoMapper implements Converter<AvaliacaoDTO, Avaliacao> {

    @Autowired
    @Lazy
    private FilmeMapper filmeMapper;

    @Autowired
    @Lazy
    private UsuarioMapper usuarioMapper;

    @Override
    public AvaliacaoDTO converterElementoDTO(Avaliacao avaliacao) {
        return AvaliacaoDTO.builder()
                .id(avaliacao.getId())
                .nota(avaliacao.getNota())
                .comentario(avaliacao.getComentario())
                .build();
    }

    @Override
    public Avaliacao converterElementoEntidade(AvaliacaoDTO dto) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());

        if(dto.getFilmeId() != null) {
            Filme filme = new Filme();
            filme.setId(dto.getFilmeId());
            avaliacao.setFilme(filme);
        }

        if(dto.getUsuarioId() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.getUsuarioId());
            avaliacao.setUsuario(usuario);
        }

        return avaliacao;
    }

 
    @Override
    public AvaliacaoDTO toFullDTO(Avaliacao avaliacao) {
        return AvaliacaoDTO.builder()
                .id(avaliacao.getId())
                .nota(avaliacao.getNota())
                .comentario(avaliacao.getComentario())
                .filme(avaliacao.getFilme() != null ? filmeMapper.converterElementoDTO(avaliacao.getFilme()) : null)
                .usuario(avaliacao.getUsuario() != null ? usuarioMapper.converterElementoDTO(avaliacao.getUsuario()) : null)
                .build();
    }
}
