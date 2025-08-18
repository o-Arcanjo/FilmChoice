package com.filmchoice.dto;

import com.filmchoice.enums.Papel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDTORecebido {

    @NotBlank(message = "O nome não pode ser nulo ou vazio.")
    private String nome;

    @NotBlank(message = "A senha não pode ser nula ou vazia.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    private String senha;

    @NotBlank(message = "O email não pode ser nulo ou vazio.")
    @Email(message = "O formato do email é inválido.")
    private String email;

    // O campo 'papel' continua sem validação, pois é definido no controller.
    private Papel papel;

    // ATUALIZADO: 'codigo' agora é obrigatório.
    @NotBlank(message = "O código não pode ser nulo ou vazio.")
    private String codigo;

    // Construtor vazio
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

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
}
