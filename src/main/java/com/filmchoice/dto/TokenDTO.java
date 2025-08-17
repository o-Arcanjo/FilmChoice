package com.filmchoice.dto;

/**
 * DTO que representa um Token com seu JWT e payload decodificado.
 */
public class TokenDTO {

    private final String token;

    private TokenDTO(Builder builder) {
        this.token = builder.token;
    }

    public String getToken() {
        return token;
    }


   public static Builder builder(){
    return new Builder();
   }

    public static class Builder {
        private String token;

        public Builder token(String token2) {
            this.token = token2;
            return this;
        }


        public TokenDTO build() {
            return new TokenDTO(this);
        }
    }
}
