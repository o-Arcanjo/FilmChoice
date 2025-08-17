package com.filmchoice.services;

import com.filmchoice.enums.Papel;

public interface AuthService {
    String criptografarSenha(String senha);
    boolean verificarSenha(String senha, String hashArmazenado);
    boolean autenticar(String token);
    boolean autorizar(String papelEsperado, String papelRecebido);
}
