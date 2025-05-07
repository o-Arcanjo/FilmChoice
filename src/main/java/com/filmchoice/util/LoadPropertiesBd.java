package com.filmchoice.util;
import java.io.InputStream;
import java.util.Properties;
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


