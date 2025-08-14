package com.filmchoice.services;

public interface TokenService {
   Payload decodificarToken(Long token, ChaveSecreta chaveSecreta);
   Long criarToken(Payload payload, ChaveSecreta chaveSecreta); 
}
