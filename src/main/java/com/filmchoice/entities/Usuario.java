package com.filmchoice.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filmchoice.enums.Papel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "Usuario", uniqueConstraints = {
        @UniqueConstraint(name = "EMAIL_USER", columnNames = { "Email" })
})
public class Usuario {

    @Id
    @GeneratedValue(generator = "jpa_usuario_seq")
    @SequenceGenerator(name = "jpa_usuario_seq", sequenceName = "usuario_id_seq")
    private Long id;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Avaliacao> avaliacoes;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "senha", nullable = false, length = 60)
    private String senha;

    @Column(name = "dataCriacao", nullable = false, updatable = false)
    private LocalDate dataCriacao;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "papel", nullable = false)
    private Papel papel;

    @Transient
    private Integer idade;

    // 🔹 Construtor padrão para JPA
    public Usuario() {
        this.dataCriacao = LocalDate.now();
    }

    // 🔹 Construtor completo opcional
    public Usuario(String nome, String senha, String email, Papel papel, Integer idade) {
        this.nome = nome;
        this.senha = senha;
        this.dataCriacao = LocalDate.now();
        this.email = email;
        this.papel = papel;
        this.idade = idade;
    }

    // 🔹 Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
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

    // 🔹 equals e hashCode baseados em id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return id != null && id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // 🔹 toString
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", papel=" + papel +
                '}';
    }
}
