package com.filmchoice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FilmeDTO {
    private Long id;
    private String titulo;
    private LocalDateTime lancamento;
    private Integer duracaoMinutos;
    private BigDecimal receita;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getLancamento() {
        return lancamento;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public BigDecimal getReceita() {
        return receita;
    }

    private FilmeDTO(Builder builder) {
        this.id = builder.id;
        this.titulo = builder.titulo;
        this.lancamento = builder.lancamento;
        this.duracaoMinutos = builder.duracaoMinutos;
        this.receita = builder.receita;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String titulo;
        private LocalDateTime lancamento;
        private Integer duracaoMinutos;
        private BigDecimal receita;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder lancamento(LocalDateTime lancamento) {
            this.lancamento = lancamento;
            return this;
        }

        public Builder duracaoMinutos(Integer duracaoMinutos) {
            this.duracaoMinutos = duracaoMinutos;
            return this;
        }

        public Builder receita(BigDecimal receita) {
            this.receita = receita;
            return this;
        }

        public FilmeDTO build() {
            return new FilmeDTO(this);
        }
    }
}
