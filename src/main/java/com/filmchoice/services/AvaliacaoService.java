package com.filmchoice.services;
import java.util.List;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dto.AvaliacaoDTO;
import com.filmchoice.entities.Avaliacao;
public interface AvaliacaoService {
    List<Avaliacao> obterRankingGlobal(String token) throws PersistenciaDawException, ServiceException;
    void fazerAvaliacao(AvaliacaoDTO avaliacao)  throws PersistenciaDawException;
    Avaliacao editarComentario(Long avaliacaoId, String novoComentario);
    Avaliacao alterarNota(Long avaliacaoId, int novaNota);
}
