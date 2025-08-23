package com.filmchoice.config;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.sql.SQLException;

@FunctionalInterface
public interface IManagerInstance<Instance extends IManagerConnection<?>> {
    Instance getInstance() throws SQLException, IOException, InvalidKeyException, ClassNotFoundException;
}
