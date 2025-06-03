package com.filmchoice.entities;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Genero")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpa_genero_seq")
    @SequenceGenerator(name = "jpa_genero_seq", sequenceName = "genero_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    // Relacionamento com Filme (um gênero pode ter vários filmes)
    @ManyToMany
    @JoinTable(name="filme_possui_genero", 
                joinColumns = @JoinColumn(name="genero"),
                inverseJoinColumns = @JoinColumn(name="filme_id"))
    private List<Filme> filmes;

    public Genero() {}

    public Genero(String tipo) {
        this.tipo = tipo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genero genero = (Genero) o;
        return Objects.equals(id, genero.id) && Objects.equals(tipo, genero.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }
}
