package com.filmchoice.entities;
import java.util.*;
import jakarta.persistence.*;
import java.math.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Filme")
public class Filme {
    @Id
    @GeneratedValue(generator="jpa_filme_seq")
    @SequenceGenerator(name="jpa_filme_seq", sequenceName="filme_id_seq")
    private Long id;    

    @Column(name="titulo", nullable=false)
    private String titulo;
    @Column(name="lancamento", updatable=false, nullable=false)
    private LocalDateTime lancamento;

    @Column(name="duracaoMinutos", updatable=false, nullable=false)
    private Integer duracaoMinutos;

    @Column(name="receita", precision=14, scale=2)
    private BigDecimal receita;

    @ManyToMany(mappedBy = "filmes")
    private List<Ator> ator;

    @ManyToMany(mappedBy = "filmes")
    private List<Diretor> diretor;

    @ManyToMany(mappedBy = "filmes")
    private List<Genero> genero;

    @ManyToMany(mappedBy = "filmes")
    private List<Idioma> idioma;

    public Filme(){};

    public Filme(String titulo, LocalDateTime lancamento, Integer duracaoMinutos, BigDecimal receita) {
        this.titulo = titulo;
        this.lancamento = lancamento;
        this.duracaoMinutos = duracaoMinutos;
        this.receita = receita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Ator> getAtores() {
        return ator;
    }
    
    public void setAtores(List<Ator> ator) {
        this.ator = ator;
    }
    
    public List<Diretor> getDiretores() {
        return diretor;
    }
    
    public void setDiretores(List<Diretor> diretor) {
        this.diretor = diretor;
    }
    
    public List<Genero> getGeneros() {
        return genero;
    }
    
    public void setGeneros(List<Genero> genero) {
        this.genero = genero;
    }
    
    public List<Idioma> getIdiomas() {
        return idioma;
    }
    
    public void setIdiomas(List<Idioma> idioma) {
        this.idioma = idioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDateTime lancamento) {
        this.lancamento = lancamento;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public BigDecimal getReceita() {
        return receita;
    }

    public void setReceita(BigDecimal receita) {
        this.receita = receita;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return id != null && id.equals(filme.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return "Filme{ " +  
                "id= " + id + 
                "Titulo= " + titulo +
                "Lancamento= " + lancamento +
                "Duracao_Minutos= " + duracaoMinutos +
                "Receita= " + receita +
                " }"; 
    }

    public Filme orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }
}
