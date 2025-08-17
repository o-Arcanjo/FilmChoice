package com.filmchoice.services.chains;

public abstract class HandlerAbstract<ProximaInstancia extends HandlerAbstract<?>> implements Handler<ProximaInstancia>{
    private final ProximaInstancia proximo;

    public HandlerAbstract(ProximaInstancia proximo){
        this.proximo = proximo;
    }

   public ProximaInstancia obterProximaInstancia(){
        return proximo;
   }

   public boolean verificarProximo(){
        if(this.obterProximaInstancia() != null){
            return this.obterProximaInstancia().verificarResponsabilidade();
        }
        return true;
   }

   public abstract boolean verificarResponsabilidade();
}


