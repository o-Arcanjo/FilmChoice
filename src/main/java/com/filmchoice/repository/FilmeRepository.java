package com.filmchoice.repository;

import com.filmchoice.entities.Filme;
import java.util.List;

public interface FilmeRepository {
    void salvar(Filme filme);

    void deletar(Filme filme);

    void atualizar(Filme filme);

    Filme buscarPorId(Long id);

    List<Filme> listarTodos();
}