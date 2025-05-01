package com.filmchoice.service;

import java.io.IOException;
import java.util.Properties;
import java.sql.*;
import com.filmchoice.util.ConfigVariavel;
import com.filmchoice.util.GetPropertiesBd;
import com.filmchoice.util.LoadPropertiesBd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManagerConnection {
    private static final Logger logger = LoggerFactory.getLogger(ManagerConnection.class);
    private static volatile ManagerConnection instance;
    private Connection connection;


     private ManagerConnection (ConfigVariavel variavel) throws SQLException, ClassNotFoundException {      
        try {
            this.connection = DriverManager.getConnection(
            variavel.getUrl(), 
            variavel.getUser(), 
            variavel.getSenha()
            );
            logger.info("Conexão com o banco estabelecida com sucesso.");
        } catch (SQLException e) {
            logger.error("Erro ao estabelecer conexão com o banco", e.getMessage());
            throw e;
        } 
    }

    private static ConfigVariavel readConfig() throws IOException{
        try{
            Properties props = LoadPropertiesBd.loadProperties();
        }catch(IOException e) {
            logger.error("Erro ao ler variáveis de ambiente", e.getMessage());
            throw e;
        } 
       return GetPropertiesBd.getInstanceConfigVariavel(props);
    }

    public static ManagerConnection getInstanceManager () {
        if(instance == null){
            synchronized (ManagerConnection.class) {
                try {
                  if(instance == null){
                    ConfigVariavel variavelConfig = ManagerConnection.readConfig();
                    instance = new ManagerConnection(variavelConfig);
                  }
                } catch (SQLException | IOException | ClassNotFoundException e) {
                    logger.error("Falha ao criar instância de ManagerConncetion: {}", e.getMessage());
                    throw new RuntimeException("Não foi possível inicializar a conexão com o banco.", e);
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException{
       if(connection == null || connection.isClosed()){
         throw new SQLException("Conexão não está disponível.");
       }
       return connection;
    }
}