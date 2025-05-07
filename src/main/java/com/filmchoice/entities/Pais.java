package com.filmchoice.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpa_pais_seq")
    @SequenceGenerator(name = "jpa_pais_seq", sequenceName = "pais_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "Nome", nullable = false, length = 60)
    private String nome;

    @Column(name = "Sigla", length = 10)
    private String sigla;

    public Pais() {}

    public Pais(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "País{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return Objects.equals(id, pais.id) && Objects.equals(nome, pais.nome) && Objects.equals(sigla, pais.sigla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sigla);
    }
}

