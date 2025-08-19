package com.filmchoice.dto;

import java.util.List;

public class IdiomaDTO {
    private Long id;
    private String tipo;
    private List<FilmeDTO> filmes; // lista de filmes

    // Getters
    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public List<FilmeDTO> getFilmes() {
        return filmes;
    }

    private IdiomaDTO(Builder builder) {
        this.id = builder.id;
        this.tipo = builder.tipo;
        this.filmes = builder.filmes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String tipo;
        private List<FilmeDTO> filmes; // lista de filmes no builder

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder filmes(List<FilmeDTO> filmes) {
            this.filmes = filmes;
            return this;
        }

        public IdiomaDTO build() {
            return new IdiomaDTO(this);
        }
    }
}
