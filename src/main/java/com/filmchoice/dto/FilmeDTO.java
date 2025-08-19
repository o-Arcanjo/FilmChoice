package com.filmchoice.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class FilmeDTO {
    private Long id;
    private String titulo;
    private LocalDateTime lancamento;
    private Integer duracaoMinutos;
    private BigDecimal receita;

    private List<AtorDTO> atores;
    private List<DiretorDTO> diretores;
    private List<GeneroDTO> generos;
    private List<IdiomaDTO> idiomas;


    public List<AtorDTO> getAtores() { return atores; }
    public List<DiretorDTO> getDiretores() { return diretores; }
    public List<GeneroDTO> getGeneros() { return generos; }
    public List<IdiomaDTO> getIdiomas() { return idiomas; }
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
        this.atores = builder.atores;
        this.diretores = builder.diretores;
        this.generos = builder.generos;
        this.idiomas = builder.idiomas;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String titulo;
        private LocalDateTime lancamento;
        private Integer duracaoMinutos;
        private BigDecimal receita;

        private List<AtorDTO> atores;
        private List<DiretorDTO> diretores;
        private List<GeneroDTO> generos;
        private List<IdiomaDTO> idiomas;



        public Builder id(Long id) { this.id = id; return this; }
        public Builder titulo(String titulo) { this.titulo = titulo; return this; }
        public Builder lancamento(LocalDateTime lancamento) { this.lancamento = lancamento; return this; }
        public Builder duracaoMinutos(Integer duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; return this; }
        public Builder receita(BigDecimal receita) { this.receita = receita; return this; }

        public Builder atores(List<AtorDTO> atores) { this.atores = atores; return this; }
        public Builder diretores(List<DiretorDTO> diretores) { this.diretores = diretores; return this; }
        public Builder generos(List<GeneroDTO> generos) { this.generos = generos; return this; }
        public Builder idiomas(List<IdiomaDTO> idiomas) { this.idiomas = idiomas; return this; }

        public FilmeDTO build() { return new FilmeDTO(this); }
    }
}
