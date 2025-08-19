package com.filmchoice.dto;

import java.time.LocalDate;
import java.util.List;

public class DiretorDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private PaisDTO pais;          // agora é PaisDTO
    private List<FilmeDTO> filmes; // agora é lista de FilmeDTO

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public PaisDTO getPais() {
        return pais;
    }

    public List<FilmeDTO> getFilmes() {
        return filmes;
    }

    private DiretorDTO(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.dataNascimento = builder.dataNascimento;
        this.pais = builder.pais;
        this.filmes = builder.filmes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String nome;
        private LocalDate dataNascimento;
        private PaisDTO pais;
        private List<FilmeDTO> filmes;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder dataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }

        public Builder pais(PaisDTO pais) {
            this.pais = pais;
            return this;
        }

        public Builder filmes(List<FilmeDTO> filmes) {
            this.filmes = filmes;
            return this;
        }

        public DiretorDTO build() {
            return new DiretorDTO(this);
        }
    }
}
