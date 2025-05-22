package com.filmchoice.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.filmchoice.enums.TipoConexao;

public final class JDBCConnection implements IManagerConnection<Connection>, IConnection {
    private static volatile JDBCConnection instance;
    private Connection connection;

    private JDBCConnection(ConfigVariavel config) throws SQLException {
        try {
            this.connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getSenha()
            );
        } catch (SQLException e) {
            throw new SQLException("Driver JDBC não encontrado: " + config.getDrive(), e);
        }
    }

    public static JDBCConnection getInstance() throws SQLException, IOException {
        if (instance == null) {
            synchronized (JDBCConnection.class) {
                if (instance == null) {
                    Properties props = LoadPropertiesBd.loadProperties(TipoConexao.JDBC);  
                    ConfigVariavel config = ConfigVariavel.builder()
                                                            .url(props.getProperty("DB_URL_JDBC"))
                                                            .user(props.getProperty("DB_USER_JDBC"))
                                                            .senha(props.getProperty("DB_SENHA_JDBC"))
                                                            .drive(props.getProperty("DB_DRIVE_JDBC"))
                                                            .build();
                    instance = new JDBCConnection(config);
                }
            }
        }
        return instance;
    }

    @Override
    public void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                instance = null; 
            }
        } catch (SQLException e) {
            
        }
    }

    @Override
    public Connection getConexao(){
        return connection;
    }
}