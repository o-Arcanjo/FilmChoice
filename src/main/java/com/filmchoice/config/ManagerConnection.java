package com.filmchoice.config;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;

import com.filmchoice.util.ConfigVariavel;
import com.filmchoice.util.GetPropertiesBd;
import com.filmchoice.util.LoadPropertiesBd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ManagerConnection {
    private static final Logger logger = LoggerFactory.getLogger(ManagerConnection.class);
    
    private ManagerConnection() {}

    private static ConfigVariavel loadConfig(TipoConexao conexao) throws IOException {
        try {
            Properties props = LoadPropertiesBd.loadProperties();
            return GetPropertiesBd.getInstanceConfigVariavel(props, conexao);
        } catch (IOException e) {
            logger.error("Erro ao carregar configurações", e);
            throw e;
        }
    }



}