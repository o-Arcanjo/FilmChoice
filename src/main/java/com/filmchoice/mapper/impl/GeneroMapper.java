package com.filmchoice.mapper.impl;

import com.filmchoice.dto.GeneroDTO;
import com.filmchoice.entities.Genero;
import com.filmchoice.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class GeneroMapper implements Converter<GeneroDTO, Genero> {

    @Autowired
    private FilmeMapper filmeMapper;

    @Override
    public GeneroDTO converterElementoDTO(Genero genero) {
        return GeneroDTO.builder()
                .id(genero.getId())
                .tipo(genero.getTipo())
                .build();
    }

    @Override
    public Genero converterElementoEntidade(GeneroDTO generoDTO) {
        Genero genero = new Genero();
        genero.setId(generoDTO.getId());
        genero.setTipo(generoDTO.getTipo());
        return genero;
    }

    @Override
    public GeneroDTO toFullDTO(Genero genero) {
        return GeneroDTO.builder()
                .id(genero.getId())
                .tipo(genero.getTipo())
                .filmes(genero.getFilmes().stream()
                    .map(filmeMapper::converterElementoDTO)
                    .collect(Collectors.toList()))
                .build();
    }
}

