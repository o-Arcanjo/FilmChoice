package com.filmchoice.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Genero_Cinematografico")
@Access(AccessType.FIELD)
@SequenceGenerator(name = "jpa_genero_seq", sequenceName = "genero_id_seq", allocationSize = 1)
public class Genero_Cinematografico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpa_genero_seq")
    private Long id;

    @Column(name = "Tipo_Genero", nullable = false, length = 50)
    private String tipoGenero;

    @Column(name = "Data_Criacao_Tipo")
    private LocalDate dataCriacaoTipo;

    public Genero_Cinematografico() {}

    public Genero_Cinematografico(String tipoGenero, LocalDate dataCriacaoTipo) {
        this.tipoGenero = tipoGenero;
        this.dataCriacaoTipo = dataCriacaoTipo;
    }

    public Long getId() {
        return id;
    }

    public String getTipoGenero() {
        return tipoGenero;
    }

    public LocalDate getDataCriacaoTipo() {
        return dataCriacaoTipo;
    }

    public void setTipoGenero(String tipoGenero) {
        this.tipoGenero = tipoGenero;
    }

    public void setDataCriacaoTipo(LocalDate dataCriacaoTipo) {
        this.dataCriacaoTipo = dataCriacaoTipo;
    }
}
