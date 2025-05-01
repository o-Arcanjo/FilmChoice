package com.filmchoice.util;
import java.util.Properties;

public final class GetPropertiesBd{
    private GetPropertiesBd(){};

    public static ConfigVariavel getInstanceConfigVariavel(Properties props){
            String dbUrl = props.getProperty("DB_URL");
            String dbUser = props.getProperty("DB_USER");
            String dbSenha = props.getProperty("DB_PASSWORD");
            return new ConfigVariavel
                .Builder()
                .url(dbUrl)
                .user(dbUser)
                .senha(dbSenha)
                .build();
    } 
    
}

