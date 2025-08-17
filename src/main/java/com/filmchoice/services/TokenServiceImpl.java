package com.filmchoice.services;

import java.sql.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.filmchoice.dto.Payload;
import com.filmchoice.enums.ChaveSecreta;
import com.filmchoice.enums.Papel;

public class TokenServiceImpl implements TokenService{
    private static final long EXPIRATION_TIME = 3600000; 
    
    @Override
    public Payload validarToken(String token, ChaveSecreta chaveSecreta) throws ServiceException {
       try {
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta.getValor());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            
        Papel papel = Papel.valueOf(jwt.getClaim("papel").asString());
        
        return Payload.builder()
                               .userId(jwt.getClaim("userId").asLong())
                               .papel(papel) 
                               .build();
        

        } catch (JWTVerificationException e) {
            throw new ServiceException("Token inválido ou expirado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Valor de papel inválido no token");
        }
       
    }

    @Override
    public boolean verificarPayload(Payload payload) {
        return payload != null &&
               payload.getUserId() != null &&
               payload.getPapel() != null; 
    }

    @Override
    public String criarToken(Payload payload, ChaveSecreta chaveSecreta) {
        if (!verificarPayload(payload)) {
            throw new IllegalArgumentException("Payload inválido");
        }
        
        Algorithm algorithm = Algorithm.HMAC256(chaveSecreta.getValor());
        
        return JWT.create()
                .withClaim("userId", payload.getUserId())
                .withClaim("papel", payload.getPapel().name())
                .withIssuedAt(new Date(0))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }
    
}
