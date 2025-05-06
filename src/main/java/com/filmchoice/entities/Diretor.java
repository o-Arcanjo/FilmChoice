package com.filmchoice.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Diretor")
@Access(AccessType.FIELD)
public class Diretor {
    @Id
    @GeneratedValue(generator = "jpa_diretor_seq")
    @SequenceGenerator(name = "jpa_diretor_seq", sequenceName = "diretor_id_seq")
    private Long id;

    @Column(name = "Nome", nullable = false, updatable=true)
    private String Nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "Data_Nascimento", nullable = false, updatable=false)
    private Date Data_Nascimento;

    public Diretor(){};

    public  Diretor(String Nome, Date Data_Nascimento) {
        this.Nome = Nome;
        this.Data_Nascimento = Data_Nascimento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return Nome;
    }

    public Date getDataNascimento() {
        return Data_Nascimento;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

}
