package com.filmchoice.dto;


public class PaisDTO {
    private Long id;
    private String nome;
    private String sigla;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    private PaisDTO(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.sigla = builder.sigla;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String nome;
        private String sigla;

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

        public PaisDTO build() {
            return new PaisDTO(this);
        }
    }
}
