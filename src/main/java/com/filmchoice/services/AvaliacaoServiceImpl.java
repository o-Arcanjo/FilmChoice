    package com.filmchoice.services;

    import java.util.List;

    import org.springframework.beans.factory.annotation.Qualifier;
    import org.springframework.stereotype.Service;

    import com.filmchoice.dao.AvaliacaoDAO;
    import com.filmchoice.dao.PersistenciaDawException;
    import com.filmchoice.dao.impl.jdbc.AvaliacaoJDBCDAOImpl;
    import com.filmchoice.dto.AvaliacaoDTO;
    import com.filmchoice.entities.Avaliacao;
    import com.filmchoice.mapper.impl.AvaliacaoMapper;

    @Service("avaliacaoServiceImpl")
    public class AvaliacaoServiceImpl implements AvaliacaoService{

        private final AvaliacaoJDBCDAOImpl avaliacaoJDBC;
        private final AvaliacaoMapper avaliacaoMapper;
        private final AvaliacaoDAO avaliacaoDAO;
        public AvaliacaoServiceImpl(
            @Qualifier("avaliacaoDAOImpl") AvaliacaoDAO avaliacaoDAO, 
            AvaliacaoMapper avaliacaoMapper,
            @Qualifier("avaliacaoJDBCDAOImpl") AvaliacaoJDBCDAOImpl avaliacaoJDBC){
            this.avaliacaoDAO = avaliacaoDAO;
            this.avaliacaoJDBC = avaliacaoJDBC;
            this.avaliacaoMapper = avaliacaoMapper;
        }

        @Override
        public void fazerAvaliacao(AvaliacaoDTO avaliacao) throws PersistenciaDawException {
            Avaliacao avaliacaoEntidade = this.avaliacaoMapper.converterElementoEntidade(avaliacao);
            this.avaliacaoDAO.save(avaliacaoEntidade);
        }

        @Override
        public Avaliacao editarComentario(Long avaliacaoId, String novoComentario) {
            throw new UnsupportedOperationException("Unimplemented method 'editarComentario'");
        }

        @Override
        public Avaliacao alterarNota(Long avaliacaoId, int novaNota) {
            throw new UnsupportedOperationException("Unimplemented method 'alterarNota'");
        }

        public List<Avaliacao> obterRankingGlobal(String token) throws PersistenciaDawException {
        return avaliacaoJDBC.obterRankingGlobal();
        }
        
    }
