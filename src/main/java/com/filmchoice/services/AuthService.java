package com.filmchoice.services;

public interface AuthService {
    boolean verificarSenha(String senha);
    boolean autenticar(Long token);
}
