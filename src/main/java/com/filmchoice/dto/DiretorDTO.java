package com.filmchoice.dto;

import java.time.LocalDate;
import java.util.List;

public class DiretorDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private Long paisId;
    private List<Long> filmesIds;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Long getPaisId() {
        return paisId;
    }

    public List<Long> getFilmesIds() {
        return filmesIds;
    }

    private DiretorDTO(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.dataNascimento = builder.dataNascimento;
        this.paisId = builder.paisId;
        this.filmesIds = builder.filmesIds;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String nome;
        private LocalDate dataNascimento;
        private Long paisId;
        private List<Long> filmesIds;

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

        public Builder paisId(Long paisId) {
            this.paisId = paisId;
            return this;
        }

        public Builder filmesIds(List<Long> filmesIds) {
            this.filmesIds = filmesIds;
            return this;
        }

        public DiretorDTO build() {
            return new DiretorDTO(this);
        }
    }
}
