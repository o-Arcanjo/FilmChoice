package com.filmchoice.config;
import java.io.InputStream;
import java.util.Properties;

import com.filmchoice.enums.TipoConexao;

import java.io.IOException;

public final class LoadPropertiesBd{
    private LoadPropertiesBd(){};
    public static Properties loadProperties(TipoConexao conexao) throws IOException {
        Properties props = new Properties();
        String path = conexao.getArquivoProperties();
       try(InputStream input = LoadPropertiesBd.class.getResourceAsStream(path)){
            if(input == null){
                throw new IOException("Arquivo config.properties não encontrado!");
            }
            props.load(input);
       }
        return props;
    }
}


