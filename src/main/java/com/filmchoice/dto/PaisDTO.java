package com.filmchoice.dto;

import java.util.List;

public class PaisDTO {
    private Long id;
    private String nome;
    private String sigla;

    private List<AtorDTO> atores;
    private List<DiretorDTO> diretores;

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public List<AtorDTO> getAtores() {
        return atores;
    }

    public List<DiretorDTO> getDiretores() {
        return diretores;
    }

    private PaisDTO(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.sigla = builder.sigla;
        this.atores = builder.atores;
        this.diretores = builder.diretores;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String nome;
        private String sigla;

        private List<AtorDTO> atores;
        private List<DiretorDTO> diretores;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder sigla(String sigla) {
            this.sigla = sigla;
            return this;
        }

        public Builder atores(List<AtorDTO> atores) {
            this.atores = atores;
            return this;
        }

        public Builder diretores(List<DiretorDTO> diretores) {
            this.diretores = diretores;
            return this;
        }

        public PaisDTO build() {
            return new PaisDTO(this);
        }
    }
}
