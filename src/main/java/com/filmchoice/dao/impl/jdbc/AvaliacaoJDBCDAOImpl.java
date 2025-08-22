    package com.filmchoice.dao.impl.jdbc;

    import java.io.IOException;
    import java.security.InvalidKeyException;
    import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
    import com.filmchoice.dao.AvaliacaoDAO;
    import com.filmchoice.dao.PersistenciaDawException;
    import com.filmchoice.entities.Avaliacao;
import com.filmchoice.entities.Filme;



    public class AvaliacaoJDBCDAOImpl extends AbstractDAOJDBCImpl<Avaliacao, Long> implements AvaliacaoDAO{

        public AvaliacaoJDBCDAOImpl() throws SQLException, InvalidKeyException, IOException {
            super();
        }


    public List<Avaliacao> obterRankingGlobal() throws PersistenciaDawException{
        String sql = """ 
    with tabela_ranking as (
        select * from obter_avaliacao_por_filmes()
    )
    select 
           ranking.filme_id, 
           ranking.filme_titulo, 
           calcular_ic_inferior(ranking.avaliacoes) as media_real
    from tabela_ranking ranking
    order by media_real desc
    ;""";
            try(PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
                List<Avaliacao> ranking = new ArrayList<>();
                while(rs.next()){
                    Filme filme = new Filme();
                    filme.setId(rs.getLong("filme_id"));
                    filme.setTitulo(rs.getString("filme_titulo"));
                    Avaliacao a = new Avaliacao();
                    a.setFilme(filme);
                    a.setMediaConfiavel(rs.getFloat("media_real"));
                    ranking.add(a);
                }
                return ranking;
            }catch(SQLException e){
                throw new PersistenciaDawException("Erro ao obter ranking global ", e);
                return null;
            }
    }

        @Override
        public void save(Avaliacao avaliacao) throws PersistenciaDawException {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'save'");
        }

        @Override
        public Avaliacao update(Avaliacao avaliacao) throws PersistenciaDawException {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'update'");
        }

        @Override
        public Avaliacao getByID(Long id) throws PersistenciaDawException {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getByID'");
        }

        @Override
        public List<Avaliacao> getAll() throws PersistenciaDawException {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getAll'");
        }

        @Override
        public void delete(Long id) throws PersistenciaDawException {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'delete'");
        }
        
    }
