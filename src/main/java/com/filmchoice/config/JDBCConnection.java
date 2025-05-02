package com.filmchoice.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.filmchoice.util.ConfigVariavel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JDBCConnection {
    private static volatile JDBCConnection instance;
    private final Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(JDBCConnection.class);

    private JDBCConnection(ConfigVariavel config) throws SQLException {
        try {
            this.connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getSenha()
            );
            logger.info("Conexão JDBC estabelecida");
        } catch (SQLException e) {
            throw new SQLException("Driver JDBC não encontrado: " + config.getDrive(), e);
        }
    }

    public static JDBCConnection getInstance() throws SQLException, IOException{
        return ManagerConnection.createSingleton(instance, JDBCConnection::new, TipoConexao.JDBC);
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                instance = null; // Permite recriar a instância se necessário
                logger.info("Conexão fechada");
            }
        } catch (SQLException e) {
            logger.error("Erro ao fechar conexão", e);
        }
    }
}