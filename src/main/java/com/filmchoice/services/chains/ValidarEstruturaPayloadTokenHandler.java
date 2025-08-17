package com.filmchoice.services.chains;

import com.filmchoice.dto.Payload;
import com.filmchoice.services.TokenService;

public class ValidarEstruturaPayloadTokenHandler extends HandlerAbstract<HandlerAbstract<?>>{
    private final Payload payload;
    private final TokenService tokenService;

    public ValidarEstruturaPayloadTokenHandler(Payload payload, TokenService tokenService){
        super(null);
        this.payload = payload;
        this.tokenService = tokenService;
    }

    @Override
    public ValidarEstruturaPayloadTokenHandler obterProximaEntidade() {
        return null;
    }

    @Override
    public boolean verificarResponsabilidade() {
        return tokenService.verificarPayload(payload);
    }
}
