package com.filmchoice.dao.impl.mongo;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.List;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dao.impl.jdbc.AvaliacaoJDBCDAOImpl;
import com.filmchoice.entities.Avaliacao;

public class ConjuntoDadosFilmes  extends AbstractDAOMONGOImpl{

    public ConjuntoDadosFilmes(AvaliacaoJDBCDAOImpl avaliacao)
            throws SQLException, InvalidKeyException, IOException, ClassNotFoundException {
        super(avaliacao);
    }

     private List<Avaliacao> obterPrimeirosCincoRankingGlobal() throws PersistenciaDawException{
        return super.avaliacao.obterPrimeirosCincoRankingGlobal();
    }
    
}
