package com.filmchoice.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Idioma")
@Access(AccessType.FIELD)
public class Idioma {

    @Id
    @GeneratedValue(generator = "jpa_idioma_seq")
    @SequenceGenerator(name = "jpa_idioma_seq", sequenceName = "idioma_id_seq")
    private Long id;

    @Column(name = "Tipo", nullable = false)
    private String tipo;

    @Temporal(TemporalType.DATE)
    @Column(name = "Data_Criacao_Tipo", nullable = false)
    private Date dataCriacaoTipo;

    @Column(name = "Principal")
    private Boolean principal;

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public Date getDataCriacaoTipo() {
        return dataCriacaoTipo;
    }

    public Boolean getPrincipal() {
        return principal;
    }
}
