package com.filmchoice.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Genero_Cinematografico")
public class Genero_Cinematografico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpa_genero_seq")
    @SequenceGenerator(name = "jpa_genero_seq", sequenceName = "genero_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    public Genero_Cinematografico() {}

    public Genero_Cinematografico(Long id,String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genero_Cinematografico that = (Genero_Cinematografico) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Genero_Cinematografico{" +
                "id=" + id +
                ", tipoGenero='" + tipo + '\'' +
                '}';
    }
}
