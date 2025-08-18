package com.filmchoice.mapper.impl;

import com.filmchoice.dto.PaisDTO;
import com.filmchoice.entities.Pais;
import com.filmchoice.entities.Ator;
import com.filmchoice.entities.Diretor;
import com.filmchoice.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaisMapper implements Converter<PaisDTO, Pais> {

    @Autowired
    private AtorMapper atorMapper;

    @Autowired
    private DiretorMapper diretorMapper;

    @Override
    public PaisDTO converterElementoDTO(Pais pais) {
        return PaisDTO.builder()
                .id(pais.getId())
                .nome(pais.getNome())
                .sigla(pais.getSigla())
                .build();
    }

    @Override
    public Pais converterElementoEntidade(PaisDTO paisDTO) {
        Pais pais = new Pais();
        pais.setId(paisDTO.getId());
        pais.setNome(paisDTO.getNome());
        pais.setSigla(paisDTO.getSigla());
        return pais;
    }
}