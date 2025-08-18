package com.filmchoice.enums;

import com.filmchoice.config.LoadProperties;

public enum CodigoAdmin  implements LoadProperties{
    Codigo("ADMIN.properties");

    private final String arquivoProperties;

    CodigoAdmin(String arquivoProperties){
        this.arquivoProperties = arquivoProperties;
    }

    public String getArquivoProperties(){
        return arquivoProperties;
    }
}
