package com.filmchoice.dao;

import com.filmchoice.config.JDBCConnection;
import com.filmchoice.entities.Genero;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO implements DAO<Genero, Long> {

    private final Connection connection;

    public GeneroDAO() throws PersistenciaDawException {
        try {
            this.connection = JDBCConnection.getInstance().getConexao();
        } catch (SQLException | IOException e) {
            throw new PersistenciaDawException("Erro ao conectar ao banco de dados", e);
        }
    }

    @Override
    public void save(Genero genero) throws PersistenciaDawException {
        String sql = "INSERT INTO Genero (tipo) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, genero.getTipo());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    genero.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao salvar gênero", e);
        }
    }

    @Override
    public Genero update(Genero genero) throws PersistenciaDawException {
        String sql = "UPDATE Genero SET tipo = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, genero.getTipo());
            stmt.setLong(2, genero.getId());
            int updated = stmt.executeUpdate();
            if (updated == 0) {
                throw new PersistenciaDawException("Nenhum gênero encontrado com ID: " + genero.getId());
            }
            return genero;
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao atualizar gênero", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        String sql = "DELETE FROM Genero WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao deletar gênero", e);
        }
    }

    @Override
    public Genero getByID(Long id) throws PersistenciaDawException {
        String sql = "SELECT * FROM Genero WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar gênero por ID", e);
        }
        return null;
    }

    @Override
    public List<Genero> getAll() throws PersistenciaDawException {
        String sql = "SELECT * FROM Genero";
        List<Genero> generos = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                generos.add(map(rs));
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar todos os gêneros", e);
        }
        return generos;
    }

    private Genero map(ResultSet rs) throws SQLException {
        Genero genero = new Genero();
        genero.setId(rs.getLong("id"));
        genero.setTipo(rs.getString("tipo"));
        return genero;
    }
}