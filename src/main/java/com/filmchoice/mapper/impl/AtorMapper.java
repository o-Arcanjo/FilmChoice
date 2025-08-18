package com.filmchoice.mapper.impl;

import com.filmchoice.dto.AtorDTO;
import com.filmchoice.entities.Ator;
import com.filmchoice.entities.Filme;
import com.filmchoice.entities.Pais;
import com.filmchoice.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AtorMapper implements Converter<AtorDTO, Ator> {

    @Autowired
    private FilmeMapper filmeMapper; // Para mapear filmes se necessário

    @Autowired
    private PaisMapper paisMapper; // Para mapear país

    @Override
    public AtorDTO converterElementoDTO(Ator ator) {
        return AtorDTO.builder()
                .id(ator.getId())
                .nome(ator.getNome())
                .dataNascimento(ator.getDataNascimento())
                .paisId(ator.getPais() != null ? ator.getPais().getId() : null)
                .filmesIds(ator.getFilmes() != null ?
                        ator.getFilmes().stream()
                                .map(Filme::getId)
                                .collect(Collectors.toList())
                        : null)
                .build();
    }

    @Override
    public Ator converterElementoEntidade(AtorDTO atorDTO) {
        Ator ator = new Ator();
        ator.setId(atorDTO.getId());
        ator.setNome(atorDTO.getNome());
        ator.setDataNascimento(atorDTO.getDataNascimento());

        // Mapeamento do País (apenas ID)
        if (atorDTO.getPaisId() != null) {
            Pais pais = new Pais();
            pais.setId(atorDTO.getPaisId());
            ator.setPais(pais);
        }

        // Nota: Filmes são geralmente adicionados via operação separada
        // para evitar carga desnecessária durante conversão

        return ator;
    }

    // Método opcional para atualização parcial
    @Override
    public void updateEntityFromDTO(AtorDTO dto, Ator entity) {
        if (dto.getNome() != null) {
            entity.setNome(dto.getNome());
        }
        if (dto.getDataNascimento() != null) {
            entity.setDataNascimento(dto.getDataNascimento());
        }
        // País só atualiza se fornecido no DTO
        if (dto.getPaisId() != null) {
            Pais pais = new Pais();
            pais.setId(dto.getPaisId());
            entity.setPais(pais);
        }
    }

    // Método adicional para mapeamento completo (com filmes)
    public AtorDTO toFullDTO(Ator ator) {
        AtorDTO dto = converterElementoDTO(ator);
        if (ator.getFilmes() != null) {
            dto.setFilmes(ator.getFilmes().stream()
                    .map(filmeMapper::converterElementoDTO)
                    .collect(Collectors.toList()));
        }
        if (ator.getPais() != null) {
            dto.setPais(paisMapper.converterElementoDTO(ator.getPais()));
        }
        return dto;
    }
}