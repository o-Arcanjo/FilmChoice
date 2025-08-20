package com.filmchoice.services;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dto.AvaliacaoDTO;

public interface UsuarioComumService{
    public void fazerAvaliacao(String token, AvaliacaoDTO avaliacao) throws ServiceException,  PersistenciaDawException ;
}
