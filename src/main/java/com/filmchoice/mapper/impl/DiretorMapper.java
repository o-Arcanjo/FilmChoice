/*package com.filmchoice.mapper.impl;

import com.filmchoice.dto.DiretorDTO;
import com.filmchoice.entities.Diretor;
import com.filmchoice.entities.Filme;
import com.filmchoice.entities.Pais;
import com.filmchoice.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiretorMapper implements Converter<DiretorDTO, Diretor> {

    @Autowired
    private PaisMapper paisMapper;

    @Autowired
    private FilmeMapper filmeMapper;

    @Override
    public DiretorDTO converterElementoDTO(Diretor diretor) {
        return DiretorDTO.builder()
                .id(diretor.getId())
                .nome(diretor.getNome())
                .dataNascimento(diretor.getDataNascimento())
                .paisId(diretor.getPais() != null ? diretor.getPais().getId() : null)
                .filmesIds(diretor.getFilmes() != null ?
                        diretor.getFilmes().stream()
                                .map(Filme::getId)
                                .collect(Collectors.toList())
                        : null)
                .build();
    }

    @Override
    public Diretor converterElementoEntidade(DiretorDTO diretorDTO) {
        Diretor diretor = new Diretor();
        diretor.setId(diretorDTO.getId());
        diretor.setNome(diretorDTO.getNome());
        diretor.setDataNascimento(diretorDTO.getDataNascimento());

        // Relacionamento com Pais (apenas ID)
        if (diretorDTO.getPaisId() != null) {
            Pais pais = new Pais();
            pais.setId(diretorDTO.getPaisId());
            diretor.setPais(pais);
        }

        // Nota: Filmes são geralmente adicionados via operação separada
        // para evitar carga desnecessária durante conversão

        return diretor;
    }
}

*/