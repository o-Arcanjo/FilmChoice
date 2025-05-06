package com.filmchoice.entities;
import jakarta.persistence.*;
import java.util.*;


@Entity
@Table(name="Usuario", uniqueConstraints={
        @UniqueConstraint(name="EMAIL_USER", columnNames={"Email"})
})
@Access(AccessType.FIELD)
public class Usuario {
    @Id
    @GeneratedValue(generator="jpa_usuario_seq")
    @SequenceGenerator(name="jpa_usuario_seq", sequenceName="usuario_id_seq")
    private Long id;

    @Column(name="Nome", nullable=false, updatable=true)
    private String Nome;

    @Column(name="Senha", nullable=false, updatable=true)
    private String Senha;

    @Column(name="Data_Criacao", nullable=false, updatable=false)
    private Date Data_Criacao;

    @Column(name="Email", nullable=false, updatable=true)
    private String Email;

    @Transient
    private Integer Idade;
    
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return true;
        Usuario usuario = (Usuario) o;
        return id != null && id.equals(usuario.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return "Usuario{ " +
                "id= " + id + 
                "Nome= " + Nome +
                "Data_Nascimento= " + Data_Criacao +
                "Senha= " + Senha + 
                "Email= " + Email + 
                " }"; 
    }
}
