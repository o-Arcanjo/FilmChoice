package com.filmchoice.dao.impl.mongo;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.filmchoice.config.ManagerFactory;
import com.filmchoice.config.MongoDBConnection;
import com.filmchoice.dao.AvaliacaoDAO;
import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dao.impl.jdbc.AvaliacaoJDBCDAOImpl;
import com.filmchoice.entities.Avaliacao;
import com.mongodb.reactivestreams.client.MongoClient;



public class AbstractDAOMONGOImpl {
    private final MongoDBConnection instanceMongoDB;
    private final MongoClient connection;
    protected final AvaliacaoJDBCDAOImpl avaliacao;


    public AbstractDAOMONGOImpl(
@Qualifier("avaliacaoJDBCDAOImpl") AvaliacaoJDBCDAOImpl avaliacao
    ) throws SQLException, InvalidKeyException, IOException, ClassNotFoundException{
        this.instanceMongoDB = ManagerFactory.get(MongoDBConnection.class);
        this.connection = instanceMongoDB.getConexao();
        this.avaliacao = avaliacao;
    }

}
