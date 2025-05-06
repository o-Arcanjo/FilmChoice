package com.filmchoice.entities;
import com.filmchoice.util.Papel;
import jakarta.persistence.*;
import java.util.*;


@Entity
@Table(name="Usuario", uniqueConstraints={
        @UniqueConstraint(name="EMAIL_USER", columnNames={"Email"})
})
public class Usuario {
    @Id
    @GeneratedValue(generator="jpa_usuario_seq")
    @SequenceGenerator(name="jpa_usuario_seq", sequenceName="usuario_id_seq")
    private Long id;

    @Column(name="Nome", nullable=false)
    private String nome;

    @Column(name="Senha", nullable=false)
    private String senha;

    @Column(name="Data_Criacao", nullable=false, updatable=false)
    private Date dataCriacao;

    @Column(name="Email", nullable=false)
    private String email;

    @Column(name="Papel", nullable = false)
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

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
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
