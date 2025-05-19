package com.filmchoice.enums;

public enum TipoConexao {
    JDBC("JDBC", "JDBC.properties"),
    MONGODB("MONGODB", "MONGODB.properties"),
    MINIO("MINIO", "MINIO.properties");

    private final String prefixo;
    private final String arquivoProperties;

     TipoConexao(String prefixo, String arquivoProperties){
        this.prefixo = prefixo;
        this.arquivoProperties = arquivoProperties;
    }

    public String getPrefixo(){
        return prefixo;
    }

    public String getArquivoProperties(){
        return arquivoProperties;
    }
}
