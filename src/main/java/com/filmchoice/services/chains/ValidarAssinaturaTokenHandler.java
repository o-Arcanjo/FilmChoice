package com.filmchoice.services.chains;

import com.filmchoice.enums.ChaveSecreta;
import com.filmchoice.services.ServiceException;
import com.filmchoice.services.TokenService;

import jakarta.validation.Payload;

public class ValidarAssinaturaTokenHandler extends HandlerAbstract<ValidarEstruturaPayloadTokenHandler> {
    private final String token;
    private final TokenService tokenService;

    public ValidarAssinaturaTokenHandler(String token, ValidarEstruturaPayloadTokenHandler validarPayload, TokenService tokenService) {
        super(validarPayload); 
        this.token = token;
        this.tokenService = tokenService;
    }

    
    @Override
    public ValidarEstruturaPayloadTokenHandler obterProximaEntidade() {
        return super.obterProximaInstancia();
    }

    @Override
    public boolean verificarResponsabilidade()  {
        try{
            Payload payload = (Payload) tokenService.validarToken(this.token, ChaveSecreta.TOKEN_JWT);
        return payload != null;
        }catch(ServiceException e){
            System.err.println("Erro ao validar token: " + e.getMessage());
            return false; 
        }
}


}
