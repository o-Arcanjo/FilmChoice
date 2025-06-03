package com.filmchoice.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name="Ator")
public class Ator {
    @Id
    @GeneratedValue(generator="jpa_ator_seq")
    @SequenceGenerator(name="jpa_ator_seq", sequenceName="ator_id_seq")
    private Long id;

    @Column(name="nome")
    private String nome;


    @Column(name="dataNascimento", updatable=false)
    private LocalDate dataNascimento;

    // Relacionamento com Filme (muitos para muitos)
    @ManyToMany
    @JoinTable(
            name = "atua",
            joinColumns = @JoinColumn(name = "ator_id"),
            inverseJoinColumns = @JoinColumn(name = "filme_id")
    )
    private List<Filme> filmes;

    // Relacionamento com País (muitos atores para um país)
    @ManyToOne
    @JoinColumn(name = "pais_id") // chave estrangeira
    private Pais pais;

    public Ator() {}

    public Ator(String nome, LocalDate dataNascimento) {
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
        Ator ator = (Ator) o;
        return id != null && Objects.equals(id, ator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ator{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}

