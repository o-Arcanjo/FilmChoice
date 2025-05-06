package com.filmchoice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Papel")
@Access(AccessType.FIELD)
@SequenceGenerator(name = "jpa_papel_seq", sequenceName = "papel_id_seq", allocationSize = 1)
public class Papel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpa_papel_seq")
    private Long id;

    @Column(name = "Valor", nullable = false, length = 60)
    private String valor;

    @Column(name = "Descricao", length = 255)
    private String descricao;

    public Papel() {}

    public Papel(String valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
