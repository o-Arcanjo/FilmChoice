package com.filmchoice.repository;

import com.filmchoice.entities.Pais;
import java.util.List;

public interface PaisRepository {
    void salvar(Pais pais);

    void deletar(Pais pais);

    void atualizar(Pais pais);

    Pais buscarPorId(Long id);

    List<Pais> listarTodos();
}
