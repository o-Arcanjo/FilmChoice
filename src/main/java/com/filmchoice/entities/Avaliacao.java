package com.filmchoice.entities;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name="Avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpa_avaliacao_seq")
    @SequenceGenerator(name = "jpa_avaliacao_seq", sequenceName = "avaliacao_id_seq", allocationSize = 1)
    private Long id;

    @Column(name="nota", nullable=false)
    private Integer nota;

    @Transient // INFORMA AO JPA PARA IGNORAR ESTE CAMPO NO BANCO DE DADOS
    private Float mediaConfiavel;

    @Lob
    @Column(name="comentario", nullable=false)
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY) // fetch = FetchType.LAZY é uma boa prática para performance
    @JoinColumn(name="filme_id")
    private Filme filme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    public Avaliacao(){}
    public Avaliacao(Integer nota, String comentario){
        this.nota = nota;
        this.comentario = comentario;
    }

    // Getters e Setters ...
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Integer getNota(){
        return nota;
    }

    public void setNota(Integer nota){
        this.nota = nota;
    }

    public Float getMediaConfiavel() {
        return mediaConfiavel;
    }

    public void setMediaConfiavel(Float mediaConfiavel){
        this.mediaConfiavel = mediaConfiavel;
    }

    public String getComentario(){
        return comentario;
    }

    public void setComentario(String comentario){
        this.comentario = comentario;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return id != null && Objects.equals(id, avaliacao.id);
    }

    @Override
    public int hashCode(){
        // Usar a classe como fallback se o id for nulo
        return getClass().hashCode();
    }

    @Override
    public String toString(){
        return "Avaliacao{ " +
                "id= " + id +
                ", Nota= " + nota +
                ", Comentario= '" + comentario + '\'' +
                " }";
    }
}
