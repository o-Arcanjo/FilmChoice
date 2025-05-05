package com.filmchoice.entities;
import java.util.*;
import jakarta.persistence.*;
import java.math.*;

@Entity
@Table(name="Filme")
@Access(AccessType.FIELD)
public class Filme {
    @Id
    @GeneratedValue(generator="jpa_filme_seq")
    @SequenceGenerator(name="jpa_filme_seq", sequenceName="filme_id_seq")
    private Long id;

    @Column(name="Titulo", nullable=false)
    private String Titulo;

    @Temporal(TemporalType.DATE)
    @Column(name="Lancamento", updatable=false, nullable=false)
    private Date Lancamento;

    @Column(name="Duracao_Minutos", updatable=false, nullable=false)
    private int Duracao_Minutos;

    @Column(name="Receita", precision=14, scale=2)
    private BigDecimal Receita;

    public Filme(){};

    public Filme(String Titulo, Date Lancamento, int Duracao_Minutos, BigDecimal Receita) {
        this.Titulo = Titulo;
        this.Lancamento = Lancamento;
        this.Duracao_Minutos = Duracao_Minutos;
        this.Receita = Receita;
    }

    public BigDecimal getReceita(){
        return Receita;
    }

    public void setReceita(BigDecimal Receita){
        this.Receita = Receita;
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
                "Titulo= " + Titulo +
                "Lancamento= " + Lancamento +
                "Duracao_Minutos= " + Duracao_Minutos + 
                "Receita= " + Receita + 
                " }"; 
    }
}
