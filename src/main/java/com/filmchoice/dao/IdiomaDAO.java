package com.filmchoice.dao;

import com.filmchoice.config.JDBCConnection;
import com.filmchoice.entities.Idioma;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdiomaDAO implements DAO<Idioma, Long> {

    private final Connection connection;

    public IdiomaDAO() throws PersistenciaDawException {
        try {
            this.connection = JDBCConnection.getInstance().getConexao();
        } catch (SQLException | IOException e) {
            throw new PersistenciaDawException("Erro ao conectar ao banco de dados", e);
        }
    }

    @Override
    public void save(Idioma idioma) throws PersistenciaDawException {
        String sql = "INSERT INTO Idioma (tipo) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, idioma.getTipo());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idioma.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao salvar idioma", e);
        }
    }

    @Override
    public Idioma update(Idioma idioma) throws PersistenciaDawException {
        String sql = "UPDATE Idioma SET tipo = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idioma.getTipo());
            stmt.setLong(2, idioma.getId());
            int updated = stmt.executeUpdate();
            if (updated == 0) {
                throw new PersistenciaDawException("Nenhum idioma encontrado com ID: " + idioma.getId());
            }
            return idioma;
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao atualizar idioma", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        String sql = "DELETE FROM Idioma WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao deletar idioma", e);
        }
    }

    @Override
    public Idioma getByID(Long id) throws PersistenciaDawException {
        String sql = "SELECT * FROM Idioma WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar idioma por ID", e);
        }
        return null;
    }

    @Override
    public List<Idioma> getAll() throws PersistenciaDawException {
        String sql = "SELECT * FROM Idioma";
        List<Idioma> idiomas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                idiomas.add(map(rs));
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar todos os idiomas", e);
        }
        return idiomas;
    }

    private Idioma map(ResultSet rs) throws SQLException {
        Idioma idioma = new Idioma();
        idioma.setId(rs.getLong("id"));
        idioma.setTipo(rs.getString("tipo"));
        return idioma;
    }
}