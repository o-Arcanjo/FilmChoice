package com.filmchoice.mapper.impl;

import com.filmchoice.dto.IdiomaDTO;
import com.filmchoice.entities.Idioma;
import com.filmchoice.entities.Filme;
import com.filmchoice.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IdiomaMapper implements Converter<IdiomaDTO, Idioma> {

    @Autowired
    private FilmeMapper filmeMapper;

    @Override
    public IdiomaDTO converterElementoDTO(Idioma idioma) {
        return IdiomaDTO.builder()
                .id(idioma.getId())
                .tipo(idioma.getTipo())
                .filmesIds(idioma.getFilmes() != null ?
                        idioma.getFilmes().stream()
                                .map(Filme::getId)
                                .collect(Collectors.toList())
                        : null)
                .build();
    }

    @Override
    public Idioma converterElementoEntidade(IdiomaDTO idiomaDTO) {
        Idioma idioma = new Idioma();
        idioma.setId(idiomaDTO.getId());
        idioma.setTipo(idiomaDTO.getTipo());

        // Relacionamentos são tratados separadamente
        return idioma;
    }
}