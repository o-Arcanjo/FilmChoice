package com.filmchoice.services.chains;

import com.filmchoice.dto.Payload;
import com.filmchoice.enums.ChaveSecreta;
import com.filmchoice.services.ServiceException;
import com.filmchoice.services.TokenService;

public class ValidarAssinaturaTokenHandler extends HandlerAbstract<ValidarEstruturaPayloadTokenHandler, String> {

    private final TokenService tokenService;

    public ValidarAssinaturaTokenHandler(ValidarEstruturaPayloadTokenHandler proximo, TokenService tokenService) {
        super(proximo);
        this.tokenService = tokenService;
    }

    private Payload obterPayload(String token) {
        try {
            return (Payload) tokenService.validarToken(token, ChaveSecreta.TOKEN_JWT);
        } catch (ServiceException e) {
            System.err.println("Erro ao validar token: " + e.getMessage());
            return null; 
        }
    }

    @Override
    public boolean verificarResponsabilidade(String token) {
        System.out.println("validando dados 3");
        System.out.println(obterPayload(token));
        return obterPayload(token) != null;
    }

    @Override
    public boolean verificarProximo(String token) {
        Payload payload = obterPayload(token);
        if (payload == null) {
            return false;
        }
        ValidarEstruturaPayloadTokenHandler proximo = obterProximaInstancia();
        return proximo == null || proximo.verificarProximo(payload);
    }
}
