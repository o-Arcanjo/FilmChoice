package com.filmchoice.entities;
import jakarta.persistence.*;
import java.time.*;

import java.util.Objects;

import com.filmchoice.enums.Papel;

@Entity
@Table(name="Usuario", uniqueConstraints={
        @UniqueConstraint(name="EMAIL_USER", columnNames={"Email"})
})
public class Usuario {
    @Id
    @GeneratedValue(generator="jpa_usuario_seq")
    @SequenceGenerator(name="jpa_usuario_seq", sequenceName="usuario_id_seq")
    private Long id;

    @Column(name="nome", nullable=false)
    private String nome;

    @Column(name="senha", nullable=false)
    private String senha;

    @Column(name="dataCriacao", nullable=false, updatable=false)
    private LocalDate dataCriacao;

    @Column(name="email", nullable=false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="papel", nullable = false)
    private Papel papel;

    @Transient
    private Integer idade;
    
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return true;
        Usuario usuario = (Usuario) o;
        return id != null && id.equals(usuario.id);
    }


    public Usuario(){}
    
    public Usuario(String nome, String senhaHash, String email, Papel papel) {
        this.nome = nome;
        this.senha = senhaHash;
        this.email = email;
        this.papel = papel;
        this.dataCriacao = LocalDate.now();
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public String toString(){
        return "Usuario{ " +
                "id= " + id + 
                "Nome= " + nome +
                "Data_Nascimento= " + dataCriacao +
                "Senha= " + senha +
                "Email= " + email +
                " }"; 
    }


}
