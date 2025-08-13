package com.filmchoice.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
    private List<Avaliacao> avaliacoes;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "senha", nullable = false)
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

    public Usuario() {
    }

    private Usuario(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.senha = builder.senha;
        this.dataCriacao = builder.dataCriacao != null ? builder.dataCriacao : LocalDate.now();
        this.email = builder.email;
        this.papel = builder.papel;
        this.idade = builder.idade;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String nome;
        private String senha;
        private LocalDate dataCriacao;
        private String email;
        private Papel papel;
        private Integer idade;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public Builder dataCriacao(LocalDate dataCriacao) {
            this.dataCriacao = dataCriacao;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder papel(Papel papel) {
            this.papel = papel;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Usuario usuario = (Usuario) o;
        return id != null && id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public String getEmail() {
        return email;
    }

    public Papel getPapel() {
        return papel;
    }

    public Integer getIdade() {
        return idade;
    }

    @Override
    public String toString() {
        return "Usuario{ " +
                "id= " + id +
                ", Nome= " + nome +
                ", Data_Nascimento= " + dataCriacao +
                ", Senha= " + senha +
                ", Email= " + email +
                " }";
    }
}