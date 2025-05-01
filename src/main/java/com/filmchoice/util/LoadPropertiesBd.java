package com.filmchoice.util;
import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

public final class LoadPropertiesBd{
    private LoadPropertiesBd(){};
    private static final String CONFIG_FILE = "/config.properties";

    public static Properties loadProperties() throws IOException {
        Properties props = new Properties();
       try(InputStream input = LoadPropertiesBd.class.getResourceAsStream(CONFIG_FILE)){
            if(input == null){
                throw new IOException("Arquivo config.properties não encontrado!");
            }
            props.load(input);
       }
        return props;
    }
}


