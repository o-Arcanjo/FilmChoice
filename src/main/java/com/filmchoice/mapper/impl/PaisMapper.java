package com.filmchoice.mapper.impl;

import com.filmchoice.dto.PaisDTO;
import com.filmchoice.entities.Pais;
import com.filmchoice.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
public PaisDTO toFullDTO(Pais pais) {
    return PaisDTO.builder()
            .id(pais.getId())
            .nome(pais.getNome())
            .sigla(pais.getSigla())
            .atores(
                pais.getAtores() != null
                    ? pais.getAtores().stream()
                        .map(atorMapper::converterElementoDTO)
                        .collect(Collectors.toList())
                    : null
            )
            .diretores(
                pais.getDiretores() != null
                    ? pais.getDiretores().stream()
                        .map(diretorMapper::converterElementoDTO)
                        .collect(Collectors.toList())
                    : null
            )
            .build();
}

}


