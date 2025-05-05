package com.filmchoice.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Diretor")
@Access(AccessType.FIELD)
@SequenceGenerator(name = "jpa_diretor_seq", sequenceName = "diretor_id_seq")
public class Diretor {

    @Id
    @GeneratedValue(generator = "jpa_diretor_seq")
    private Long id;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "Data_Nascimento", nullable = false)
    private Date dataNascimento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idNacionalidade")
    private Nacionalidade nacionalidade;

    @ManyToMany
    @JoinTable(
            name = "dirige",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Filme> filmes;

    public Diretor() {
    }

    public Diretor(String nome, Date dataNascimento, Nacionalidade nacionalidade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Nacionalidade getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(Nacionalidade nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }
}
