package com.filmchoice.dao;

import com.filmchoice.config.JDBCConnection;
import com.filmchoice.entities.Filme;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO implements DAO<Filme, Long> {

    private final Connection connection;

    public FilmeDAO() throws PersistenciaDawException {
        try {
            this.connection = JDBCConnection.getInstance().getConexao();
        } catch (SQLException | IOException e) {
            throw new PersistenciaDawException("Erro ao conectar com o banco de dados", e);
        }
    }

    @Override
    public void save(Filme filme) throws PersistenciaDawException {
        String sql = "INSERT INTO Filme (titulo, lancamento, duracaoMinutos, receita) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, filme.getTitulo());
            stmt.setTimestamp(2, Timestamp.valueOf(filme.getLancamento()));
            stmt.setInt(3, filme.getDuracaoMinutos());
            stmt.setBigDecimal(4, filme.getReceita());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    filme.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao salvar Filme", e);
        }
    }

    @Override
    public Filme update(Filme filme) throws PersistenciaDawException {
        String sql = "UPDATE Filme SET titulo = ?, lancamento = ?, duracaoMinutos = ?, receita = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, filme.getTitulo());
            stmt.setTimestamp(2, Timestamp.valueOf(filme.getLancamento()));
            stmt.setInt(3, filme.getDuracaoMinutos());
            stmt.setBigDecimal(4, filme.getReceita());
            stmt.setLong(5, filme.getId());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new PersistenciaDawException("Nenhum filme encontrado com ID: " + filme.getId());
            }
            return filme;
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao atualizar Filme", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        String sql = "DELETE FROM Filme WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao deletar Filme", e);
        }
    }

    @Override
    public Filme getByID(Long id) throws PersistenciaDawException {
        String sql = "SELECT * FROM Filme WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar Filme por ID", e);
        }
        return null;
    }

    @Override
    public List<Filme> getAll() throws PersistenciaDawException {
        String sql = "SELECT * FROM Filme";
        List<Filme> filmes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                filmes.add(map(rs));
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar todos os Filmes", e);
        }
        return filmes;
    }

    private Filme map(ResultSet rs) throws SQLException {
        Filme filme = new Filme();
        filme.setId(rs.getLong("id"));
        filme.setTitulo(rs.getString("titulo"));
        filme.setLancamento(rs.getTimestamp("lancamento").toLocalDateTime());
        filme.setDuracaoMinutos(rs.getInt("duracaoMinutos"));
        filme.setReceita(rs.getBigDecimal("receita"));
        return filme;
    }
}