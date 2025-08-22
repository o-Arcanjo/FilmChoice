package com.filmchoice.config;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import java.util.Optional;

public class ManagerFactory {
    private static final Map<Class<?>, IManagerInstance<?>> factories = new HashMap<>();

    static {
        factories.put(JDBCConnection.class, JDBCConnection :: getInstance);
        factories.put(MongoDBConnection.class, MongoDBConnection :: getInstance);
        factories.put(MinIoConnection.class, MinIoConnection :: getInstance);
    }
        
    public static <T extends IManagerConnection<?>> T get(Class<T> clazz) 
    throws SQLException, IOException, InvalidKeyException{
       Optional<IManagerInstance<?>> factory = Optional.ofNullable(factories.get(clazz));
       return (T) factory
                  .orElseThrow(() -> new IllegalArgumentException("Classe Não Registrada na Factory" + clazz.getName()))
                  .getInstance();
    }

}
