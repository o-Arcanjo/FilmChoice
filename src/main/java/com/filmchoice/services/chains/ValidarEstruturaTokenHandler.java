package com.filmchoice.services.chains;
import com.filmchoice.dto.TokenDTO;
import com.filmchoice.enums.TokenEsperado;
import com.filmchoice.middlewares.ValidarDados;

public class ValidarEstruturaTokenHandler extends HandlerAbstract<ValidarAssinaturaTokenHandler>{
    private final String token;
    private  ValidarDados validarDados;

    public  ValidarEstruturaTokenHandler(String token, ValidarAssinaturaTokenHandler assinaturaToken){
        super(assinaturaToken);
        this.token = token;
        this.validarDados = new ValidarDados<>(TokenEsperado.TOKEN.getRegex(), converterEmDTO());
    }


    @Override
    public ValidarAssinaturaTokenHandler obterProximaEntidade() {
     return super.obterProximaInstancia();
    }


    private TokenDTO converterEmDTO(){
        return TokenDTO.builder()
        .token(this.token)
        .build();
    }


    @Override
    public boolean verificarResponsabilidade() {
        return validarDados.validarCampos();                    
    }
}
