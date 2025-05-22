package com.filmchoice.dao;

import com.filmchoice.config.JDBCConnection;
import com.filmchoice.entities.Usuario;
import com.filmchoice.enums.Papel;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario, Long> {

    private final Connection connection;

    public UsuarioDAO() throws PersistenciaDawException {
        try {
            this.connection = JDBCConnection.getInstance().getConexao();
        } catch (SQLException | IOException e) {
            throw new PersistenciaDawException("Erro ao conectar com o banco", e);
        }
    }

    @Override
    public void save(Usuario usuario) throws PersistenciaDawException {
        String sql = "INSERT INTO Usuario (nome, senha, dataCriacao, email, papel) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setDate(3, Date.valueOf(usuario.getDataCriacao()));
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getPapel().name());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao salvar usuário", e);
        }
    }

    @Override
    public Usuario update(Usuario usuario) throws PersistenciaDawException {
        String sql = "UPDATE Usuario SET nome = ?, senha = ?, email = ?, papel = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getPapel().name());
            stmt.setLong(5, usuario.getId());
            stmt.executeUpdate();
            return usuario;
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao atualizar usuário", e);
        }
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao deletar usuário", e);
        }
    }

    @Override
    public Usuario getByID(Long id) throws PersistenciaDawException {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar usuário por ID", e);
        }
        return null;
    }

    @Override
    public List<Usuario> getAll() throws PersistenciaDawException {
        String sql = "SELECT * FROM Usuario";
        List<Usuario> lista = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(map(rs));
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar todos os usuários", e);
        }
        return lista;
    }

    private Usuario map(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
        usuario.setEmail(rs.getString("email"));
        usuario.setPapel(Papel.valueOf(rs.getString("papel")));
        return usuario;
    }
}
