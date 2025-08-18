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

    @Override
    public void updateEntityFromDTO(PaisDTO dto, Pais entity) {
        if (dto.getNome() != null) {
            entity.setNome(dto.getNome());
        }
        if (dto.getSigla() != null) {
            entity.setSigla(dto.getSigla());
        }
    }

    // Método para DTO detalhado com relacionamentos
    public PaisDTO toFullDTO(Pais pais) {
        PaisDTO dto = converterElementoDTO(pais);

        if (pais.getAtores() != null) {
            dto.setAtores(pais.getAtores().stream()
                    .map(atorMapper::converterElementoDTO)
                    .collect(Collectors.toList()));
        }

        if (pais.getDiretores() != null) {
            dto.setDiretores(pais.getDiretores().stream()
                    .map(diretorMapper::converterElementoDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}