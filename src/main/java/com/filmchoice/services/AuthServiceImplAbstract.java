package com.filmchoice.services;
import com.filmchoice.services.chains.ConstruirCadeiaHandlerToken;
import com.filmchoice.services.chains.Handler;
import com.filmchoice.services.chains.ValidarAssinaturaTokenHandler;


public abstract class AuthServiceImplAbstract<T extends TokenService> implements AuthService {
    private final T serviceAuth;

    public AuthServiceImplAbstract(T serviceAuth) {
        this.serviceAuth = serviceAuth;

    }

    public boolean autenticar(String token) {
            Handler<ValidarAssinaturaTokenHandler, String> tokenValidationChain = ConstruirCadeiaHandlerToken.construirCadeia(serviceAuth);
            return tokenValidationChain.verificarProximo(token);
    }

    public  boolean autorizar(String papelEsperado, String papelRecebido){
           return papelEsperado.equals(papelRecebido);
    }
}