
package com.filmchoice.services.chains;

public abstract class HandlerAbstract<ProximaInstancia extends Handler<?, ?>, T>
        implements Handler<ProximaInstancia, T> {

    private final ProximaInstancia proximo;

    public HandlerAbstract(ProximaInstancia proximo) {
        this.proximo = proximo;
    }

    @Override
    public ProximaInstancia obterProximaInstancia() {
        return proximo;
    }    
}