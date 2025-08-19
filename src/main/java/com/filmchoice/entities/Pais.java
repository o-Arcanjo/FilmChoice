package com.filmchoice.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Pais")
public class Pais {

    @Id
    @GeneratedValue(generator = "jpa_pais_seq")
    @SequenceGenerator(name = "jpa_pais_seq", sequenceName = "pais_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nome", nullable = false, length = 60)
    private String nome;

    @Column(name = "sigla", length = 10)
    private String sigla;

    // Relacionamento com atores
    @OneToMany(mappedBy = "pais")
    private List<Ator> atores;

    // Relacionamento com diretores
    @OneToMany(mappedBy = "pais")
    private List<Diretor> diretores;

    public Pais() {}

    public Pais(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public List<Ator> getAtores() { return atores; }
    public void setAtores(List<Ator> atores) { this.atores = atores; }

    public List<Diretor> getDiretores() { return diretores; }
    public void setDiretores(List<Diretor> diretores) { this.diretores = diretores; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return Objects.equals(id, pais.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }
}

