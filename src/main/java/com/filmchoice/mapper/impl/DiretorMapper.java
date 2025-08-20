package com.filmchoice.mapper.impl;
import com.filmchoice.dto.DiretorDTO;
import com.filmchoice.entities.Diretor;
import com.filmchoice.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Lazy;

@Component
public class DiretorMapper implements Converter<DiretorDTO, Diretor> {

    @Autowired
    @Lazy
    private PaisMapper paisMapper;

    @Autowired
    @Lazy
    private FilmeMapper filmeMapper;

    @Override
    public DiretorDTO converterElementoDTO(Diretor diretor) {
        return DiretorDTO.builder()
                .id(diretor.getId())
                .nome(diretor.getNome())
                .dataNascimento(diretor.getDataNascimento())
                .build();
    }

    @Override
    public Diretor converterElementoEntidade(DiretorDTO diretorDTO) {
        Diretor diretor = new Diretor();
        diretor.setId(diretorDTO.getId());
        diretor.setNome(diretorDTO.getNome());
        diretor.setDataNascimento(diretorDTO.getDataNascimento());
        return diretor;
    }

    @Override
    public DiretorDTO toFullDTO(Diretor diretor) {
        return DiretorDTO.builder()
        .id(diretor.getId())
        .nome(diretor.getNome())
        .dataNascimento(diretor.getDataNascimento())
        .pais(paisMapper.converterElementoDTO(diretor.getPais()))
        .filmes(diretor.getFilmes().stream()
        .map(filmeMapper::converterElementoDTO)
        .collect(Collectors.toList()))
        .build();
    }
}

