package com.filmchoice.dao;

import com.filmchoice.config.JDBCConnection;
import com.filmchoice.entities.Diretor;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiretorDAO implements DAO<Diretor, Long> {

    private final Connection connection;

    public DiretorDAO() throws PersistenciaDawException {
        try {
            this.connection = JDBCConnection.getInstance().getConexao();
        } catch (SQLException | IOException e) {
            throw new PersistenciaDawException("Erro ao obter conexão com o banco", e);
        }
    }

    @Override
    public void save(Diretor diretor) throws PersistenciaDawException {
        String sql = "INSERT INTO Diretor (nome, dataNascimento) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, diretor.getNome());
            stmt.setDate(2, Date.valueOf(diretor.getDataNascimento()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    diretor.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao salvar Diretor", e);
        }
    }

    @Override
    public Diretor update(Diretor diretor) throws PersistenciaDawException {
        String sql = "UPDATE Diretor SET nome = ?, dataNascimento = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, diretor.getNome());
            stmt.setDate(2, Date.valueOf(diretor.getDataNascimento()));
            stmt.setLong(3, diretor.getId());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new PersistenciaDawException("Diretor não encontrado com ID: " + diretor.getId());
            }
            return diretor;
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao atualizar Diretor", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        String sql = "DELETE FROM Diretor WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao deletar Diretor", e);
        }
    }

    @Override
    public Diretor getByID(Long id) throws PersistenciaDawException {
        String sql = "SELECT * FROM Diretor WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar Diretor", e);
        }
        return null;
    }

    @Override
    public List<Diretor> getAll() throws PersistenciaDawException {
        String sql = "SELECT * FROM Diretor";
        List<Diretor> lista = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(map(rs));
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar todos os Diretores", e);
        }
        return lista;
    }

    private Diretor map(ResultSet rs) throws SQLException {
        Diretor diretor = new Diretor();
        diretor.setId(rs.getLong("id"));
        diretor.setNome(rs.getString("nome"));
        diretor.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
        return diretor;
    }
}