package com.filmchoice.services;
import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dto.AvaliacaoDTO;
import com.filmchoice.entities.Avaliacao;
public interface AvaliacaoService {
    void fazerAvaliacao(AvaliacaoDTO avaliacao)  throws PersistenciaDawException;
    Avaliacao editarComentario(Long avaliacaoId, String novoComentario);
    Avaliacao alterarNota(Long avaliacaoId, int novaNota);
}
