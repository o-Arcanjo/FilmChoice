package com.filmchoice.dto;

import com.filmchoice.enums.Papel;

public class UsuarioDTO {
    private Long id; // NOVO
    private String nome;
    private String senha;
    private String email;
    private Papel papel;
    private String codigo;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getCodigo(){
        return codigo;
    }

    public String getEmail() {
        return email;
    }

    public Papel getPapel() {
        return papel;
    }

    public UsuarioDTO(Builder builder){
        this.id = builder.id; // NOVO
        this.nome = builder.nome;
        this.senha = builder.senha;
        this.email = builder.email;
        this.papel = builder.papel;
        this.codigo = builder.codigo;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Long id; // NOVO
        private String nome;
        private String senha;
        private String email;
        private Papel papel;
        private String codigo;

        public Builder id(Long id) { // NOVO
            this.id = id;
            return this;
        }

        public Builder nome(String nome){
            this.nome = nome;
            return this;
        }

        public Builder senha(String senha){
            this.senha = senha;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder papel(Papel papel){
            this.papel = papel;
            return this;
        }

        public Builder codigo(String codigo){
            this.codigo = codigo;
            return this;
        }

        public UsuarioDTO build(){
            return new UsuarioDTO(this);
        }
    }
}
