package com.filmchoice.repository;

import com.filmchoice.entities.Genero;
import java.util.List;

public interface GeneroRepository {
    void salvar(Genero genero);

    void deletar(Genero genero);

    void atualizar(Genero genero);

    Genero buscarPorId(Long id);

    List<Genero> listarTodos();
}
