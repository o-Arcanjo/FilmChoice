package com.filmchoice.entities;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="Avaliacao")
@Access(AccessType.FIELD)
public class Avaliacao {
    @Id
    @GeneratedValue(generator = "jpa_avaliacao_seq")
    @SequenceGenerator(name = "jpa_avaliacao_seq", sequenceName = "avaliacao_id_seq")
    private Long id;

    @Column(name="Nota", nullable=false, updatable=true)
    private int Nota;

    @Lob
    @Column(name="Comentario", nullable=false, updatable=true)
    private String Comentario;

    public Avaliacao(){}
    public Avaliacao(int Nota, String Comentario){
        this.Nota = Nota;
        this.Comentario = Comentario;
    }

    public Long getId(){
        return id;
    }

    public int getNota(){
        return Nota;
    }

    public String getComentario(){
        return Comentario;
    }

    public void setNota(int Nota){
        this.Nota = Nota;
    }

    public void setComentario(String Comentario){
        this.Comentario = Comentario;
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return id != null && id.equals(avaliacao.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return "Avaliacao{ " +
                "id= " + id + 
                "Nota= " + Nota +
                "Comentario= " + Comentario + 
                " }"; 
    }
}
