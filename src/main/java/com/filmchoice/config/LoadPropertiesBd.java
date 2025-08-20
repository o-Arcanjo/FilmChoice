package com.filmchoice.config;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class LoadPropertiesBd{
    private LoadPropertiesBd(){};
    public static<T extends LoadProperties> Properties loadProperties(T conexao) throws IOException {
        Properties props = new Properties();
        String path = conexao.getArquivoProperties();
       try(InputStream input = LoadPropertiesBd.class.getResourceAsStream("/" + path)){
            if(input == null){
                throw new IOException("Arquivo config.properties não encontrado!");
            }
            props.load(input);
       }
        return props;
    }
}


