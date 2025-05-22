package com.filmchoice.dao;

import com.filmchoice.config.JDBCConnection;
import com.filmchoice.entities.Ator;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtorDAO implements DAO<Ator, Long> {

    private final Connection connection;

    public AtorDAO() throws PersistenciaDawException {
        try {
            this.connection = JDBCConnection.getInstance().getConexao();
        } catch (SQLException | IOException e) {
            throw new PersistenciaDawException("Erro ao conectar ao banco de dados", e);
        }
    }

    @Override
    public void save(Ator ator) throws PersistenciaDawException {
        String sql = "INSERT INTO Ator (nome, dataNascimento) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ator.getNome());
            stmt.setDate(2, Date.valueOf(ator.getDataNascimento()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    ator.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao salvar Ator", e);
        }
    }

    @Override
    public Ator update(Ator ator) throws PersistenciaDawException {
        String sql = "UPDATE Ator SET nome = ?, dataNascimento = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ator.getNome());
            stmt.setDate(2, Date.valueOf(ator.getDataNascimento()));
            stmt.setLong(3, ator.getId());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new PersistenciaDawException("Nenhum ator encontrado com ID: " + ator.getId());
            }
            return ator;
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao atualizar Ator", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        String sql = "DELETE FROM Ator WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao deletar Ator com ID: " + id, e);
        }
    }

    @Override
    public Ator getByID(Long id) throws PersistenciaDawException {
        String sql = "SELECT * FROM Ator WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAtor(rs);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar Ator por ID", e);
        }
        return null;
    }

    @Override
    public List<Ator> getAll() throws PersistenciaDawException {
        List<Ator> lista = new ArrayList<>();
        String sql = "SELECT * FROM Ator";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapResultSetToAtor(rs));
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar todos os Atores", e);
        }
        return lista;
    }

    private Ator mapResultSetToAtor(ResultSet rs) throws SQLException {
        Ator ator = new Ator();
        ator.setId(rs.getLong("id"));
        ator.setNome(rs.getString("nome"));
        ator.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
        return ator;
    }
}
