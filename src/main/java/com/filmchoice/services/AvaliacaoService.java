package com.filmchoice.services;

import com.filmchoice.entities.Avaliacao;
import com.filmchoice.entities.Filme;

public interface AvaliacaoService {
    Avaliacao fazerAvaliacao(Filme filme, int nota, String comentario);
    public void editarComentario(String novoComentario);
    public void alterarNota(int novaNota);  
}
