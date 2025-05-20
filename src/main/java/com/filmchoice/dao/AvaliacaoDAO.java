package com.filmchoice.dao;

import com.filmchoice.config.JDBCConnection;
import com.filmchoice.entities.Avaliacao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO implements DAO<Avaliacao, Long> {

    private final Connection connection;

    public AvaliacaoDAO() throws PersistenciaDawException {
        try {
            this.connection = JDBCConnection.getInstance().getConexao();
        } catch (SQLException | IOException e) {
            throw new PersistenciaDawException("Erro ao conectar ao banco de dados", e);
        }
    }

    @Override
    public void save(Avaliacao avaliacao) throws PersistenciaDawException {
        String sql = "INSERT INTO Avaliacao (nota, comentario) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, avaliacao.getNota());
            stmt.setString(2, avaliacao.getComentario());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    avaliacao.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao salvar Avaliação", e);
        }
    }

    @Override
    public Avaliacao update(Avaliacao avaliacao) throws PersistenciaDawException {
        String sql = "UPDATE Avaliacao SET nota = ?, comentario = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, avaliacao.getNota());
            stmt.setString(2, avaliacao.getComentario());
            stmt.setLong(3, avaliacao.getId());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new PersistenciaDawException("Nenhuma avaliação encontrada com ID: " + avaliacao.getId());
            }
            return avaliacao;
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao atualizar Avaliação", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        String sql = "DELETE FROM Avaliacao WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao deletar Avaliação com ID: " + id, e);
        }
    }

    @Override
    public Avaliacao getByID(Long id) throws PersistenciaDawException {
        String sql = "SELECT * FROM Avaliacao WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAvaliacao(rs);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar Avaliação por ID", e);
        }
        return null;
    }

    @Override
    public List<Avaliacao> getAll() throws PersistenciaDawException {
        List<Avaliacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM Avaliacao";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapResultSetToAvaliacao(rs));
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar todas as Avaliações", e);
        }
        return lista;
    }

    private Avaliacao mapResultSetToAvaliacao(ResultSet rs) throws SQLException {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(rs.getLong("id"));
        avaliacao.setNota(rs.getInt("nota"));
        avaliacao.setComentario(rs.getString("comentario"));
        return avaliacao;
    }
}