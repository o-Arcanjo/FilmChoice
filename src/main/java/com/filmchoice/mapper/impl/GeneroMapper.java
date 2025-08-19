/*package com.filmchoice.mapper.impl;

import com.filmchoice.dto.GeneroDTO;
import com.filmchoice.entities.Genero;
import com.filmchoice.entities.Filme;
import com.filmchoice.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
                .filmesIds(genero.getFilmes() != null ?
                        genero.getFilmes().stream()
                                .map(Filme::getId)
                                .collect(Collectors.toList())
                        : null)
                .build();
    }

    @Override
    public Genero converterElementoEntidade(GeneroDTO generoDTO) {
        Genero genero = new Genero();
        genero.setId(generoDTO.getId());
        genero.setTipo(generoDTO.getTipo());

        // Relacionamentos são tratados separadamente
        return genero;
    }
}

*/