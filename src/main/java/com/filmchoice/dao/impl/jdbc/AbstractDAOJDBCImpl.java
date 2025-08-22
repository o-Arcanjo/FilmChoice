package com.filmchoice.dao.impl.jdbc;
import com.filmchoice.config.JDBCConnection;
import com.filmchoice.config.ManagerFactory;
import com.filmchoice.dao.DAO;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAOJDBCImpl<E,T> implements DAO<E, T>{
    private final JDBCConnection instanceJDBC;
    protected final Connection connection;

    public AbstractDAOJDBCImpl() throws SQLException, InvalidKeyException, IOException{
        this.instanceJDBC = ManagerFactory.get(JDBCConnection.class);
        this.connection = instanceJDBC.getConexao();
    }

    protected JDBCConnection getInstanceJDBC() {
        return instanceJDBC;
    }
}
