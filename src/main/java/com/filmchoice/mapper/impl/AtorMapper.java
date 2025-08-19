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
}