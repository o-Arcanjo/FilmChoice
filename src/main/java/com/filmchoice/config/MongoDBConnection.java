package com.filmchoice.config;

import java.io.IOException;
import java.util.Properties;

import com.filmchoice.enums.TipoConexao;

public class MongoDBConnection implements IManagerConnection{
    private static MongoDBConnection instance;
    

    private MongoDBConnection (ConfigVariavel config){
        String urlMongo = "mongodb+srv://" + config.getUser() + ":" + config.getSenha() + "@cluster0.cj0xje5.mongodb.net/";
        
    }

    public static MongoDBConnection getInstance() throws IOException{
        if(instance == null){
            synchronized(MongoDBConnection.class){
                if(instance == null){
                     Properties props = LoadPropertiesBd.loadProperties(TipoConexao.MONGODB);  
                     ConfigVariavel config = new ConfigVariavel.Builder()
                                                               .user(props.getProperty("DB_USER_MONGODB"))
                                                               .senha(props.getProperty("DB_SENHA_MONGODB"))
                                                               .dbName(props.getProperty("DB_NOME_MONGODB"))
                                                               .build();
                     instance = new MongoDBConnection(config);
                }
            }
        }
        return instance;
    }

    public void conectar(){};

    public void desconectar(){};
}
