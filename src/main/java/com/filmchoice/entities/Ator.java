package com.filmchoice.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Ator")
@Access(AccessType.FIELD)
public class Ator {
    @Id
    @GeneratedValue(generator="jpa_ator_seq")
    @SequenceGenerator(name="jpa_ator_seq", sequenceName="ator_id_seq")
    private Long id;

    @Column(name="Nome")
    private String Nome;

    @Temporal(TemporalType.DATE)
    @Column(name="Data_Nascimento", updatable=false)
    private Date dataNascimento;

    // Construtor padrão
    public Ator() {}

    // Construtor completo
    public Ator(String nome, Date dataNascimento) {
        this.Nome = nome;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
}

