package com.filmchoice.services.chains;

public interface Handler<ProximaInstancia, T> {
    ProximaInstancia obterProximaInstancia();
    boolean verificarResponsabilidade(T responsabilidade);
    boolean verificarProximo(T responsabilidade);
}