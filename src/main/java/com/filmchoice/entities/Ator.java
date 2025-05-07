package com.filmchoice.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Ator")
public class Ator {

    @Id
    @GeneratedValue(generator = "jpa_ator_seq")
    @SequenceGenerator(name = "jpa_ator_seq", sequenceName = "ator_id_seq")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "Data_Nascimento", updatable = false)
    private LocalDateTime dataNascimento;

    // Construtor padrão
    public Ator() {}

    // Construtor completo
    public Ator(String nome, LocalDateTime dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
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

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Ator{" +
                "id=" + id +
                ", Nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ator ator = (Ator) o;
        return Objects.equals(id, ator.id) &&
                Objects.equals(nome, ator.nome) &&
                Objects.equals(dataNascimento, ator.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, dataNascimento);
    }
}
