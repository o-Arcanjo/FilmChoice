package com.filmchoice.services.chains;

import com.filmchoice.dto.TokenDTO;
import com.filmchoice.enums.TokenEsperado;
import com.filmchoice.middlewares.ValidarDados;



public class ValidarEstruturaTokenHandler 
        extends HandlerAbstract<ValidarAssinaturaTokenHandler, String> {

    private ValidarDados validarDados;
    

    public ValidarEstruturaTokenHandler(ValidarAssinaturaTokenHandler proximo) {
        super(proximo);
    }

    private TokenDTO converterEmDTO(String token) {
        return TokenDTO.builder()
                .token(token)
                .build();
    }

    @Override
    public boolean verificarResponsabilidade(String token) {
        System.out.println("validando dados");
        this.validarDados = new ValidarDados(TokenEsperado.TOKEN.getRegex(), converterEmDTO(token));
        System.out.println(this.validarDados + " validados dados");
        System.out.println(this.validarDados.validarCampos());
        return this.validarDados.validarCampos();
    }

    @Override
    public boolean verificarProximo(String token) {
        System.out.println("Verificar estrutura do token");
        if (!this.verificarResponsabilidade(token)) {
            return false;
        }
        return obterProximaInstancia() == null || obterProximaInstancia().verificarProximo(token);
    }
}



