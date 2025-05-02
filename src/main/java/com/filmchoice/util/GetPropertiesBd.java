package com.filmchoice.util;
import java.util.Properties;
import com.filmchoice.config.TipoConexao;

public final class GetPropertiesBd{
    private GetPropertiesBd(){};

    public static ConfigVariavel getInstanceConfigVariavel(Properties props, TipoConexao conexao){
            String prefixo = conexao.toString().toLowerCase() + ".";
            String dbUrl = props.getProperty(prefixo + "DB_URL");
            String dbUser = props.getProperty(prefixo + "DB_USER");
            String dbSenha = props.getProperty(prefixo + "DB_PASSWORD");
            return new ConfigVariavel
                .Builder()
                .url(dbUrl)
                .user(dbUser)
                .senha(dbSenha)
                .build();
    } 
    
}

