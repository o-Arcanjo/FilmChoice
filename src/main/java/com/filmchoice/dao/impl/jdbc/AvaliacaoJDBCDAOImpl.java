package com.filmchoice.dao.impl.jdbc;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.filmchoice.dao.AvaliacaoDAO;
import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.entities.Ator;
import com.filmchoice.entities.Avaliacao;
import com.filmchoice.entities.Diretor;
import com.filmchoice.entities.Filme;
import com.filmchoice.entities.Genero;
import com.filmchoice.entities.Idioma;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class AvaliacaoJDBCDAOImpl extends AbstractDAOJDBCImpl<Avaliacao, Long> implements AvaliacaoDAO {

    public AvaliacaoJDBCDAOImpl() throws SQLException, InvalidKeyException, IOException, ClassNotFoundException {
        super();
    }

   
    public List<Avaliacao> obterRankingGlobal() throws PersistenciaDawException {
        String sql = """
            WITH tabela_ranking AS (
                SELECT * FROM obter_avaliacoes_filmes()
            )
            SELECT 
                ranking.filme_id, 
                ranking.filme_titulo, 
                ranking.atores,
                ranking.diretores,
                ranking.generos,
                ranking.idiomas,
                calcular_ic_inferior(ranking.avaliacoes) AS media_real
            FROM tabela_ranking ranking
            ORDER BY media_real DESC;
        """;

        try (PreparedStatement ps = this.connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<Avaliacao> ranking = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();

            while (rs.next()) {
                Filme filme = new Filme();
                try {
                    String atoresJson = rs.getString("atores");
                    String diretoresJson = rs.getString("diretores");
                    String generosJson = rs.getString("generos");
                    String idiomasJson = rs.getString("idiomas");

                
                    List<Ator> atores = mapper.readValue(atoresJson, new TypeReference<List<Ator>>() {});
                    List<Diretor> diretores = mapper.readValue(diretoresJson, new TypeReference<List<Diretor>>() {});
                    List<Genero> generos = mapper.readValue(generosJson, new TypeReference<List<Genero>>() {});
                    List<Idioma> idiomas = mapper.readValue(idiomasJson, new TypeReference<List<Idioma>>() {});

                  
                    filme.setAtores(atores);
                    filme.setDiretores(diretores);
                    filme.setGeneros(generos);
                    filme.setIdiomas(idiomas);
                    filme.setId(rs.getLong("filme_id"));
                    filme.setTitulo(rs.getString("filme_titulo"));

                   
                    Avaliacao a = new Avaliacao();
                    a.setFilme(filme);
                    a.setMediaConfiavel(rs.getFloat("media_real"));
                    ranking.add(a);
                } catch (IOException e) {
                    throw new PersistenciaDawException("Erro ao desserializar JSON", e);
                }
            }
            return ranking;
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao obter ranking global", e);
        }
    }

  
    @Override
    public void save(Avaliacao avaliacao) throws PersistenciaDawException {
        throw new UnsupportedOperationException("Método não implementado");
    }

    @Override
    public Avaliacao update(Avaliacao avaliacao) throws PersistenciaDawException {
        throw new UnsupportedOperationException("Método não implementado");
    }

    @Override
    public Avaliacao getByID(Long id) throws PersistenciaDawException {
        throw new UnsupportedOperationException("Método não implementado");
    }

    @Override
    public List<Avaliacao> getAll() throws PersistenciaDawException {
        throw new UnsupportedOperationException("Método não implementado");
    }

    @Override
    public void delete(Long id) throws PersistenciaDawException {
        throw new UnsupportedOperationException("Método não implementado");
    }
}
