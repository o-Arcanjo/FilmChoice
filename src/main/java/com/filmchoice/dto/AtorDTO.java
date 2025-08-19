package com.filmchoice.dto;
import java.time.LocalDate;
import java.util.List;

import com.filmchoice.dto.*;

public class AtorDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    
    // Relacionamentos
    private Long paisId;
    private List<Long> filmesIds;

    // Objetos completos para DTO detalhado
    private PaisDTO pais;
    private List<FilmeDTO> filmes;

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public Long getPaisId() { return paisId; }
    public List<Long> getFilmesIds() { return filmesIds; }
    public PaisDTO getPais() { return pais; }
    public List<FilmeDTO> getFilmes() { return filmes; }

    private AtorDTO(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.dataNascimento = builder.dataNascimento;
        this.paisId = builder.paisId;
        this.filmesIds = builder.filmesIds;
        this.pais = builder.pais;
        this.filmes = builder.filmes;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String nome;
        private LocalDate dataNascimento;
        private Long paisId;
        private List<Long> filmesIds;
        private PaisDTO pais;
        private List<FilmeDTO> filmes;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder nome(String nome) { this.nome = nome; return this; }
        public Builder dataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; return this; }
        public Builder paisId(Long paisId) { this.paisId = paisId; return this; }
        public Builder filmesIds(List<Long> filmesIds) { this.filmesIds = filmesIds; return this; }
        public Builder pais(PaisDTO pais) { this.pais = pais; return this; }
        public Builder filmes(List<FilmeDTO> filmes) { this.filmes = filmes; return this; }

        public AtorDTO build() { return new AtorDTO(this); }
    }
}
