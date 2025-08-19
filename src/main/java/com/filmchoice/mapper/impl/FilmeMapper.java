package com.filmchoice.mapper.impl;
import com.filmchoice.dto.FilmeDTO;
import com.filmchoice.entities.*;
import com.filmchoice.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class FilmeMapper implements Converter<FilmeDTO, Filme> {

    @Autowired
    private AtorMapper atorMapper;

    @Autowired
    private DiretorMapper diretorMapper;

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private IdiomaMapper idiomaMapper;

    @Override
    public FilmeDTO converterElementoDTO(Filme filme) {
        return FilmeDTO.builder()
                .id(filme.getId())
                .titulo(filme.getTitulo())
                .lancamento(filme.getLancamento())
                .duracaoMinutos(filme.getDuracaoMinutos())
                .receita(filme.getReceita())
                .build();
    }

    @Override
    public Filme converterElementoEntidade(FilmeDTO filmeDTO) {
        Filme filme = new Filme();
        filme.setId(filmeDTO.getId());
        filme.setTitulo(filmeDTO.getTitulo());
        filme.setLancamento(filmeDTO.getLancamento());
        filme.setDuracaoMinutos(filmeDTO.getDuracaoMinutos());
        filme.setReceita(filmeDTO.getReceita());

        return filme;
    }

    @Override
public FilmeDTO toFullDTO(Filme filme) {
    return FilmeDTO.builder()
            .id(filme.getId())
            .titulo(filme.getTitulo())
            .lancamento(filme.getLancamento())
            .duracaoMinutos(filme.getDuracaoMinutos())
            .receita(filme.getReceita())
            .atores(
                filme.getAtores() != null
                    ? filme.getAtores().stream()
                        .map(atorMapper::converterElementoDTO)
                        .collect(Collectors.toList())
                    : null
            )
            .diretores(
                filme.getDiretores() != null
                    ? filme.getDiretores().stream()
                        .map(diretorMapper::converterElementoDTO)
                        .collect(Collectors.toList())
                    : null
            )
            .generos(
                filme.getGeneros() != null
                    ? filme.getGeneros().stream()
                        .map(generoMapper::converterElementoDTO)
                        .collect(Collectors.toList())
                    : null
            )
            .idiomas(
                filme.getIdiomas() != null
                    ? filme.getIdiomas().stream()
                        .map(idiomaMapper::converterElementoDTO)
                        .collect(Collectors.toList())
                    : null
            )
            .build();
}

}

