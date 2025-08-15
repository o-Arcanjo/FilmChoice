package com.filmchoice.dto;

import com.filmchoice.enums.Papel;

public class UsuarioDTORecebido {

    private String nome;
    private String senha;
    private String email;
    private Papel papel;

    // Construtor vazio é necessário para o Spring instanciar a classe
    public UsuarioDTORecebido() {}

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Papel getPapel() { return papel; }
    public void setPapel(Papel papel) { this.papel = papel; }
}
