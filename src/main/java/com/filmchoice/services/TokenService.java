package com.filmchoice.services;

import com.filmchoice.dto.Payload;
import com.filmchoice.enums.ChaveSecreta;

public interface TokenService {
   Payload decodificarToken(Long token, ChaveSecreta chaveSecreta);
   Long criarToken(Payload payload, ChaveSecreta chaveSecreta); 
}
