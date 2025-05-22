package com.filmchoice.dao;

import com.filmchoice.config.JDBCConnection;
import com.filmchoice.entities.Pais;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO implements DAO<Pais, Long> {

    private final Connection connection;

    public PaisDAO() throws PersistenciaDawException {
        try {
            this.connection = JDBCConnection.getInstance().getConexao();
        } catch (SQLException | IOException e) {
            throw new PersistenciaDawException("Erro ao obter conexão com o banco de dados", e);
        }
    }

    @Override
    public void save(Pais pais) throws PersistenciaDawException {
        String sql = "INSERT INTO Pais (nome, sigla) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pais.getNome());
            stmt.setString(2, pais.getSigla());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pais.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao salvar país", e);
        }
    }

    @Override
    public Pais update(Pais pais) throws PersistenciaDawException {
        String sql = "UPDATE Pais SET nome = ?, sigla = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pais.getNome());
            stmt.setString(2, pais.getSigla());
            stmt.setLong(3, pais.getId());
            stmt.executeUpdate();
            return pais;
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao atualizar país", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        String sql = "DELETE FROM Pais WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao deletar país", e);
        }
    }

    @Override
    public Pais getByID(Long id) throws PersistenciaDawException {
        String sql = "SELECT * FROM Pais WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar país por ID", e);
        }
        return null;
    }

    @Override
    public List<Pais> getAll() throws PersistenciaDawException {
        List<Pais> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pais";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(map(rs));
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar todos os países", e);
        }
        return lista;
    }

    private Pais map(ResultSet rs) throws SQLException {
        Pais pais = new Pais();
        pais.setId(rs.getLong("id"));
        pais.setNome(rs.getString("nome"));
        pais.setSigla(rs.getString("sigla"));
        return pais;
    }
}