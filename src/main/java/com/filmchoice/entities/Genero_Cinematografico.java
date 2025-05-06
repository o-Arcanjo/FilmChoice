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

    @Column(name = "Tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "Data_Criacao_Tipo")
    private LocalDate dataCriacaoTipo;

    public Genero_Cinematografico() {}

    public Genero_Cinematografico(String tipo, LocalDate dataCriacaoTipo) {
        this.tipo = tipo;
        this.dataCriacaoTipo = dataCriacaoTipo;
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

    public LocalDate getDataCriacaoTipo() {
        return dataCriacaoTipo;
    }

    public void setDataCriacaoTipo(LocalDate dataCriacaoTipo) {
        this.dataCriacaoTipo = dataCriacaoTipo;
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
                ", dataCriacaoTipo=" + dataCriacaoTipo +
                '}';
    }
}
