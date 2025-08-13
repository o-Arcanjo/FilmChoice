package com.filmchoice.repository;

import com.filmchoice.entities.Avaliacao;
import java.util.List;

public interface AvaliacaoRepository {
    void salvar(Avaliacao avaliacao);

    void deletar(Avaliacao avaliacao);

    void atualizar(Avaliacao avaliacao);

    Avaliacao buscarPorId(Long id);

    List<Avaliacao> listarTodos();
}
