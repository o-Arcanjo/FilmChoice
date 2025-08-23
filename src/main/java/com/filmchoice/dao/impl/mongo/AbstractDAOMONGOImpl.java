package com.filmchoice.dao.impl.mongo;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Qualifier;
import com.filmchoice.config.ManagerFactory;
import com.filmchoice.config.MongoDBConnection;
import com.filmchoice.dao.impl.jdbc.AvaliacaoJDBCDAOImpl;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoDatabase;



public class AbstractDAOMONGOImpl {
    private final MongoDBConnection instanceMongoDB;
    private final MongoClient connection;
    protected final AvaliacaoJDBCDAOImpl avaliacao;
    protected final MongoDatabase mongoDatabase;

    public AbstractDAOMONGOImpl(
@Qualifier("avaliacaoJDBCDAOImpl") AvaliacaoJDBCDAOImpl avaliacao
    ) throws SQLException, InvalidKeyException, IOException, ClassNotFoundException{
        this.instanceMongoDB = ManagerFactory.get(MongoDBConnection.class);
        this.connection = instanceMongoDB.getConexao();
        instanceMongoDB.conectar();
        this.mongoDatabase = instanceMongoDB.getDatabase();
        this.avaliacao = avaliacao;
    }

}
