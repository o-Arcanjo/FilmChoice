package com.filmchoice.services.proxies;

import java.util.List;

import org.springframework.stereotype.Component;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dto.AvaliacaoDTO;
import com.filmchoice.entities.Avaliacao;
import com.filmchoice.services.AuthService;
import com.filmchoice.services.AvaliacaoService;
import com.filmchoice.services.ServiceException;


@Component("avaliacaoComumProxy")
public class AvaliacaoComumProxy implements AvaliacaoService {

    private final AuthService auth;
    private final AvaliacaoService avaliacaoService;

    public AvaliacaoComumProxy(AuthService auth, AvaliacaoService avaliacaoService) {
        this.auth = auth;
        this.avaliacaoService = avaliacaoService;
    }

    @Override
    public List<Avaliacao> obterRankingGlobal(String token) throws PersistenciaDawException, ServiceException {
        if (!auth.autenticar(token)) {
            throw new ServiceException("Erro na autenticação");
        }
        return avaliacaoService.obterRankingGlobal(token);
    }

    @Override
    public void fazerAvaliacao(AvaliacaoDTO avaliacao) throws PersistenciaDawException {
        throw new UnsupportedOperationException("Método não implementado");
    }

    @Override
    public Avaliacao editarComentario(Long avaliacaoId, String novoComentario) {
        throw new UnsupportedOperationException("Método não implementado");
    }

    @Override
    public Avaliacao alterarNota(Long avaliacaoId, int novaNota) {
        throw new UnsupportedOperationException("Método não implementado");
    }
}
