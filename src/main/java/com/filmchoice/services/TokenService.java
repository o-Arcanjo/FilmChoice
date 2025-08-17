package com.filmchoice.services;

import com.filmchoice.dto.Payload;
import com.filmchoice.enums.ChaveSecreta;

public interface TokenService {
   Payload validarToken(String token, ChaveSecreta chaveSecreta) throws ServiceException;
   boolean verificarPayload(Payload payload);
   String criarToken(Payload payload, ChaveSecreta chaveSecreta); 
}
