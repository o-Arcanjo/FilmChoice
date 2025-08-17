package com.filmchoice.services.chains;
import com.filmchoice.dto.Payload;
import com.filmchoice.services.TokenService;

public class ValidarEstruturaPayloadTokenHandler 
extends HandlerAbstract<ValidarEstruturaPayloadTokenHandler, Payload>{
    private final TokenService tokenService;

    public ValidarEstruturaPayloadTokenHandler(TokenService tokenService){
        super(null);
        this.tokenService = tokenService;
    }

    @Override
    public ValidarEstruturaPayloadTokenHandler obterProximaInstancia() { 
        return null;
    }

    @Override
    public boolean verificarResponsabilidade(Payload payload) {
        return tokenService.verificarPayload(payload);
    }

    @Override
    public boolean verificarProximo(Payload responsabilidade) {
        tokenService.verificarPayload(responsabilidade);
        return true;
    }
}
