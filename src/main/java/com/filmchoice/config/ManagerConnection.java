package com.filmchoice.config;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;

import com.filmchoice.util.ConfigVariavel;
import com.filmchoice.util.GetPropertiesBd;
import com.filmchoice.util.LoadPropertiesBd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ManagerConnection {
    private static final Logger logger = LoggerFactory.getLogger(ManagerConnection.class);
    
    private ManagerConnection() {}

    /**
     * Carrega as configurações do arquivo properties
     */
    private static ConfigVariavel loadConfig(TipoConexao conexao) throws IOException {
        try {
            Properties props = LoadPropertiesBd.loadProperties();
            return GetPropertiesBd.getInstanceConfigVariavel(props, conexao);
        } catch (IOException e) {
            logger.error("Erro ao carregar configurações", e);
            throw e;
        }
    }

    /**
     * Método genérico para criação de Singletons que precisam de ConfigVariavel
     * @param <T> Tipo do Singleton
     * @param instance Instância atual (null na primeira vez)
     * @param factory Função que cria a instância usando ConfigVariavel
     * @return Instância do Singleton
     */
      /**
     * Versão que aceita funções que lançam exceções
     */
    public static <T, E extends Exception> T createSingleton(
        T instance, 
        ThrowingFunction<ConfigVariavel, T, E> factory, TipoConexao conexao
    ) throws E, IOException {
        if (instance == null) {
            synchronized (ManagerConnection.class) {
                if (instance == null) {
                    try {
                        ConfigVariavel config = loadConfig(conexao);
                        instance = factory.apply(config);
                        logger.info("Singleton criado com sucesso");
                    } catch (Exception e) {
                        logger.error("Falha na criação do Singleton", e);
                        throw e;
                    }
                }
            }
        }
        return instance;
    }
}