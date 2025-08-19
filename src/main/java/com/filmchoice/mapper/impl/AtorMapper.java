package com.filmchoice.mapper.impl;
import com.filmchoice.dto.AtorDTO;
import com.filmchoice.entities.Ator;
import com.filmchoice.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class AtorMapper implements Converter<AtorDTO, Ator> {

    @Autowired
    private FilmeMapper filmeMapper; 

    @Autowired
    private PaisMapper paisMapper;

    @Override
    public AtorDTO converterElementoDTO(Ator ator) {
        return AtorDTO.builder()
                .id(ator.getId())
                .nome(ator.getNome())
                .dataNascimento(ator.getDataNascimento())
                .build();
    }

    @Override
    public Ator converterElementoEntidade(AtorDTO atorDTO) {
        Ator ator = new Ator();
        ator.setId(atorDTO.getId());
        ator.setNome(atorDTO.getNome());
        ator.setDataNascimento(atorDTO.getDataNascimento());

        return ator;
    }

    @Override
public AtorDTO toFullDTO(Ator ator) {
    return AtorDTO.builder()
            .id(ator.getId())
            .nome(ator.getNome())
            .dataNascimento(ator.getDataNascimento())
            .filmes(
                ator.getFilmes() != null
                    ? ator.getFilmes().stream()
                        .map(filmeMapper::converterElementoDTO) 
                        .collect(Collectors.toList())
                    : null
            )
            .pais(
                ator.getPais() != null 
                    ? paisMapper.converterElementoDTO(ator.getPais()) 
                    : null
            )
            .build();
}

}


