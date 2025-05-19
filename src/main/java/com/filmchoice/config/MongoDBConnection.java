package com.filmchoice.config;

import java.io.IOException;
import java.util.Properties;

import org.bson.Document;

import com.filmchoice.enums.TipoConexao;
import com.mongodb.*;

import reactor.core.publisher.Mono;


public class MongoDBConnection implements IManagerConnection<MongoClient>, IConnection{
    private static MongoDBConnection instance;
    private MongoClient connection;
    private String nomeDataBase;
    private MongoDatabase database;

    
    private ServerApi inicieServerApi(){
        return ServerApi.builder()
        .version(ServerApiVersion.V1)
        .build();
    }

    private MongoDBConnection (ConfigVariavel config) throws MongoException{
        String urlMongo = "mongodb+srv://" + config.getUser() + ":" + config.getSenha() +
         "@" + config.getHost() + "/" + config.getDatabase() + "?retryWrites=true&w=majority&appName=" + config.getAppName();

        String nomeDataBase = config.getDatabase();
        ConnectionString connectionString =  new ConnectionString(urlMongo);
        MongoClientSettings settings = configure(connectionString);
        try {
            this.connection = MongoClients.create(settings);
            this.database = connection.getDatabase(nomeDataBase);
        } catch (MongoException e) {
            throw new MongoException("Erro na conexão mongoDb", e);
        }
    }

    public static MongoDBConnection getInstance() throws IOException{
        if(instance == null){
            synchronized(MongoDBConnection.class){
                if(instance == null){
                     Properties props = LoadPropertiesBd.loadProperties(TipoConexao.MONGODB);  
                     ConfigVariavel config = ConfigVariavel.builder()
                                                               .user(props.getProperty("DB_USER_MONGODB"))
                                                               .senha(props.getProperty("DB_SENHA_MONGODB"))
                                                               .dbHost(props.getProperty("DB_NOME_MONGODB"))
                                                               .appName(props.getProperty("DB_APP_MONGODB"))
                                                               .database(props.getProperty("DB_DATABASE_MONGODB"))
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
        MongoCollection<Document> collection = this.database.getCollection("teste");
        Mono.from(collection.countDocuments())
            .doOnNext(count -> System.out.println("Banco de Dados Conectado com Sucesso! Documentos na coleção: " + count))
            .doOnError(error -> System.err.println("Erro ao conectar ao banco: " + error.getMessage()))
            .subscribe();
    };

    @Override
    public void desconectar(){
        this.connection.close();
        instance = null;
    };

    @Override
    public MongoClient getConexao(){
        return connection;
    };

    private MongoDatabase getDatabase(){
      return database;
    };

}
