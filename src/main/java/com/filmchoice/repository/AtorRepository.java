package com.filmchoice.repository;

import com.filmchoice.entities.Ator;
import java.util.List;

public interface AtorRepository {
    void salvar(Ator ator);

    void deletar(Ator ator);

    void atualizar(Ator ator);

    Ator buscarPorId(Long id);

    List<Ator> listarTodos();
}
