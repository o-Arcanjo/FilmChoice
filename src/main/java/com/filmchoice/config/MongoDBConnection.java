package com.filmchoice.config;

import java.io.IOException;
import java.util.Properties;
import com.filmchoice.enums.TipoConexao;

import com.mongodb.*;
import com.mongodb.reactivestreams.client.MongoCollection;
import org.bson.Document;
import reactor.core.publisher.Mono;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;


public class MongoDBConnection implements IManagerConnection{
    private static MongoDBConnection instance;
    private mongoClient connection;
    private String nomeDataBase;
    private String database;

    
    private ServerApi inicieServerApi(){
        return ServerApi.builder()
        .version(ServerApiVersion.V1)
        .build();
    }

    private MongoDBConnection (ConfigVariavel config) throws MongoException{
        String urlMongo = "mongodb+srv://" + config.getUser() + ":" + config.getSenha() +
         "@" + config.getHost() + "/?retryWrites=true&w=majority&appName=" + config.getAppName();

        String nomeDataBase = config.getAppName();
        ConnectionString connectionString =  new ConnectionString(urlMongo);
        MongoClientSettings settings = configure(connectionString);
        try {
            this.mongoClient = MongoClients.create(settings);
            this.database = mongoClient.getDatabase(this.nomeDatabase);
        } catch (MongoException e) {
            throw new MongoException("Erro na conexão mongoDb", e);
        }
    }

    public static MongoDBConnection getInstance() throws IOException{
        if(instance == null){
            synchronized(MongoDBConnection.class){
                if(instance == null){
                     Properties props = LoadPropertiesBd.loadProperties(TipoConexao.MONGODB);  
                     ConfigVariavel config = new ConfigVariavel.Builder()
                                                               .user(props.getProperty("DB_USER_MONGODB"))
                                                               .senha(props.getProperty("DB_SENHA_MONGODB"))
                                                               .dbHost(props.getProperty("DB_NOME_MONGODB"))
                                                               .appName(props.getProperty("DB_APP_MONGODB"))
                                                               .build();
                     instance = new MongoDBConnection(config);
                }
            }
        }
        return instance;
    }

    private MongoClientSettings configure (ConnectionString connection){
        return MongoClientSettings.builder()
        .applyConnectionString(connection)
        .serverApi(inicieServerApi())
        .build();
    }

    public void conectar(){
        
       
    };

    private MongoDatabase getDatabase(){
      
    }

    public void desconectar(){};
}
