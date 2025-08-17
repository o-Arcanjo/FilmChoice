package com.filmchoice.services;

public interface AuthService {
    String criptografarSenha(String senha);
    boolean verificarSenha(String senha, String hashArmazenado);
    boolean autenticar(Long token);
}
