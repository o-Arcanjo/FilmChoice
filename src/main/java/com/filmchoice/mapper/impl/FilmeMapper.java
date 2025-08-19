package com.filmchoice.mapper.impl;

import com.filmchoice.dto.FilmeDTO;
import com.filmchoice.entities.*;
import com.filmchoice.mapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
                .atoresIds(filme.getAtores() != null ?
                        filme.getAtores().stream()
                                .map(Ator::getId)
                                .collect(Collectors.toList())
                        : null)
                .diretoresIds(filme.getDiretores() != null ?
                        filme.getDiretores().stream()
                                .map(Diretor::getId)
                                .collect(Collectors.toList())
                        : null)
                .generosIds(filme.getGeneros() != null ?
                        filme.getGeneros().stream()
                                .map(Genero::getId)
                                .collect(Collectors.toList())
                        : null)
                .idiomasIds(filme.getIdiomas() != null ?
                        filme.getIdiomas().stream()
                                .map(Idioma::getId)
                                .collect(Collectors.toList())
                        : null)
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

        // Relacionamentos são tratados separadamente
        return filme;
    }
}