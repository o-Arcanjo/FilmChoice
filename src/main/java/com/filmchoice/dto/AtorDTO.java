package com.filmchoice.dto;
import java.time.LocalDate;

public class AtorDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    private AtorDTO(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.dataNascimento = builder.dataNascimento;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String nome;
        private LocalDate dataNascimento;

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

        public AtorDTO build() {
            return new AtorDTO(this);
        }
    }
}

