package com.filmchoice.services.chains;

import com.filmchoice.services.TokenService;
// Adicione a importação da sua implementação concreta do TokenService
// Exemplo: import com.filmchoice.services.TokenServiceImpl;

/**
 * Classe utilitária (Factory) para construir e fornecer uma instância
 * da cadeia de responsabilidade para validação de tokens.
 * Esta classe garante que a cadeia seja montada corretamente em um único lugar.
 */
public final class ConstruirCadeiaHandlerToken {

    // Impede a instanciação da classe utilitária.
    private ConstruirCadeiaHandlerToken() {
        throw new UnsupportedOperationException("Classe utilitária, não pode ser instanciada");
    }

    /**
     * Constrói e retorna o primeiro elo da cadeia de validação de token.
     * A cadeia é reutilizável. Os dados (token) são passados no momento da execução.
     *
     * @param tokenService A implementação do serviço de token necessária para as validações.
     * @return O primeiro handler da cadeia, pronto para ser usado.
     */
    public static Handler<ValidarAssinaturaTokenHandler, String> construirCadeia(TokenService tokenService) {
        
        // Passo 1: Criar a cadeia de trás para frente para injetar as dependências.

        // Elo 3: Valida a estrutura do Payload. É o fim da cadeia.
        ValidarEstruturaPayloadTokenHandler terceiroHandler = 
            new ValidarEstruturaPayloadTokenHandler(tokenService);

        // Elo 2: Valida a assinatura do token e o transforma em Payload. Aponta para o elo 3.
        ValidarAssinaturaTokenHandler segundoHandler = 
            new ValidarAssinaturaTokenHandler(terceiroHandler, tokenService);

        // Elo 1: Valida a estrutura da String do token. É o início da cadeia. Aponta para o elo 2.
        ValidarEstruturaTokenHandler primeiroHandler = 
            new ValidarEstruturaTokenHandler(segundoHandler);

        // Passo 2: Retornar o primeiro elo.
        return primeiroHandler;
    }
}
