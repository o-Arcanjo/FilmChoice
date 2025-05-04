package com.filmchoice.entities;
import java.util.Date;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name="Filme")
@Access(AccessType.FIELD)
public class Filme {
    @Id
    @GeneratedValue(generator="jpa_filme_seq")
    @SequenceGenerator(name="jpa_filme_seq", sequenceName="filme_id_seq")
    private Long id;

    @Column(name="Titulo")
    private String Titulo;

    @Temporal(TemporalType.DATE)
    @Column(name="Lancamento", updatable=false)
    private Date Lancamento;

}
