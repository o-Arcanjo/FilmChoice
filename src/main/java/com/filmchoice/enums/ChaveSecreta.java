package com.filmchoice.enums;

public enum ChaveSecreta {
    TOKEN_JWT("$T3i<x6H@j9N/0q");
    
    private final String valor;
    
    ChaveSecreta(String valor) {
        this.valor = valor;
    }
    
    public String getValor() {
        return valor;
    }
}