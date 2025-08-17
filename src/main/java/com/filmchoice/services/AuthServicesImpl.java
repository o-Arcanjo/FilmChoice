package com.filmchoice.services;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.filmchoice.enums.ChaveSecreta;

@Service
public class AuthServicesImpl extends AuthServiceImplAbstract<TokenService> {
    private final BCryptPasswordEncoder encoder;

     public AuthServicesImpl(TokenService tokenService, ChaveSecreta chave) {
        super(tokenService, chave);  
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String criptografarSenha(String senha) {
        return encoder.encode(senha);
    }

    @Override
    public boolean verificarSenha(String senha, String hashArmazenado) {
        return encoder.matches(senha, hashArmazenado);
    }

    @Override
    public boolean autenticar(String token) {
        return super.autenticar(token);
    }

    @Override
    public boolean autorizar(String papelEsperado, String papelRecebido) {
       return super.autorizar(papelEsperado, papelRecebido);
    }
    
}
