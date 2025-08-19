package com.filmchoice.model;

import java.util.List;

public class LocalReal implements LocalInterface{
    private final Local local;
    public LocalReal(Local local) {
        this.local = local;
    }

    @Override
    public String getImagemUrl() {
        return local.getImagemUrl();
    }

    @Override
    public List<Double> getCoordenadas() {
        return local.getCoordenadas();
    }

    @Override
    public String getNomeImagem() {
       return local.getNomeImagem();
    }
    
}
