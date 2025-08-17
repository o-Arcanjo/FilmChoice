package com.filmchoice.services;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthServicesImpl implements AuthService{
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String criptografarSenha(String senha) {
        return encoder.encode(senha);
    }

    @Override
    public boolean verificarSenha(String senha, String hashArmazenado) {
        return encoder.matches(senha, hashArmazenado);
    }

    @Override
    public boolean autenticar(Long token) {
        throw new UnsupportedOperationException("Unimplemented method 'autenticar'");
    }
    
}
