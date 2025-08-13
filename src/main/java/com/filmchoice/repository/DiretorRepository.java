package com.filmchoice.repository;

import com.filmchoice.entities.Diretor;
import java.util.List;

public interface DiretorRepository {
    void salvar(Diretor diretor);

    void deletar(Diretor diretor);

    void atualizar(Diretor diretor);

    Diretor buscarPorId(Long id);

    List<Diretor> listarTodos();
}
