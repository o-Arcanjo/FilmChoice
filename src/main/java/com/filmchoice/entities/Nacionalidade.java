package com.filmchoice.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Nacionalidade")
@Access(AccessType.FIELD)
@SequenceGenerator(name = "jpa_nacionalidade_seq", sequenceName = "nacionalidade_id_seq", allocationSize = 1)
public class Nacionalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpa_nacionalidade_seq")
    private Long id;

    @Column(name = "Nome", nullable = false, length = 60)
    private String nome;

    @Column(name = "Data_Tipo")
    private LocalDate dataTipo;

    @Column(name = "Sigla", length = 10)
    private String Sigla;

    public Nacionalidade() {}

    public Nacionalidade(String nome, LocalDate dataTipo, String sigla) {
        this.nome = nome;
        this.dataTipo = dataTipo;
        this.Sigla = sigla;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataTipo() {
        return dataTipo;
    }

    public void setDataTipo(LocalDate dataTipo) {
        this.dataTipo = dataTipo;
    }

    public String getSigla() {
        return Sigla;
    }

    public void setSigla(String sigla) {
        this.Sigla = Sigla;
    }
}
