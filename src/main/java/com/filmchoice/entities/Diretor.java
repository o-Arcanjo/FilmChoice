package com.filmchoice.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "Diretor")
public class Diretor {
    @Id
    @GeneratedValue(generator = "jpa_diretor_seq")
    @SequenceGenerator(name = "jpa_diretor_seq", sequenceName = "diretor_id_seq")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;


    @Column(name = "dataNascimento", nullable = false, updatable=false)
    private LocalDate dataNascimento;


    @ManyToOne
    @JoinColumn(name="pais_id")
    private Pais pais;

    @ManyToMany
    @JoinTable(
               name="dirige",
               joinColumns = @JoinColumn(name="dirige_id"),
               inverseJoinColumns = @JoinColumn(name="filme_id")
               )
    private List<Filme> filmes; 

    public Diretor(){};

    public  Diretor(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
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

    public Pais getPais(){
        return pais;
    }

    public List<Filme> getFilmes(){
        return filmes;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diretor diretor = (Diretor) o;
        return id != null && Objects.equals(id, diretor.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
