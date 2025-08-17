package com.filmchoice.services.chains;

public interface Handler <ProximaInstancia> {
    ProximaInstancia obterProximaEntidade();
    boolean verificarResponsabilidade();
    boolean verificarProximo();
}
