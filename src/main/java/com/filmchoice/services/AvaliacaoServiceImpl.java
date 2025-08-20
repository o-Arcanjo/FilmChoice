package com.filmchoice.services;

import org.springframework.stereotype.Service;

import com.filmchoice.dao.AvaliacaoDAO;
import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dto.AvaliacaoDTO;
import com.filmchoice.entities.Avaliacao;
import com.filmchoice.mapper.impl.AvaliacaoMapper;


@Service
public class AvaliacaoServiceImpl implements AvaliacaoService{

    private final AvaliacaoMapper avaliacaoMapper;
    private final AvaliacaoDAO avaliacaoDAO;
    public AvaliacaoServiceImpl(AvaliacaoDAO avaliacaoDAO, AvaliacaoMapper avaliacaoMapper){
        this.avaliacaoDAO = avaliacaoDAO;
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
    
}
