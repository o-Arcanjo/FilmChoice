package com.filmchoice.repository;

import com.filmchoice.entities.Idioma;
import java.util.List;

public interface IdiomaRepository {
    void salvar(Idioma idioma);

    void deletar(Idioma idioma);

    void atualizar(Idioma idioma);

    Idioma buscarPorId(Long id);

    List<Idioma> listarTodos();
}
