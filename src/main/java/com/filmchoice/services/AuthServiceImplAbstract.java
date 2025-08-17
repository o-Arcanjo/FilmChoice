package com.filmchoice.services;
import com.filmchoice.dto.Payload;
import com.filmchoice.enums.ChaveSecreta;

public abstract class AuthServiceImplAbstract<T extends TokenService> implements AuthService {
    private final T serviceAuth;
    private final ChaveSecreta chave;

    public AuthServiceImplAbstract(T serviceAuth, ChaveSecreta chave) {
        this.serviceAuth = serviceAuth;
        this.chave = chave;
    }

    public boolean autenticar(String token) {
        try{
            Payload payload = this.serviceAuth.validarToken(token, chave);
            return this.serviceAuth.verificarPayload(payload);
        }catch(ServiceException e){
            return false;
        }   
    }

    public  boolean autorizar(String papelEsperado, String papelRecebido){
           return papelEsperado.equals(papelRecebido);
    }
}