package com.filmchoice.dto;

import java.util.List;

public class LocalDTOResponse {
    private List<Double> coordenadas;
    private String nomeImagem;
    private String imagemUrl;

    public LocalDTOResponse(List<Double> coordenadas, String nomeImagem, String imagemUrl){
        this.coordenadas = coordenadas;
        this.nomeImagem = nomeImagem;
        this.imagemUrl = imagemUrl;
    }

    // Getters
    public List<Double> getCoordenadas() {
        return coordenadas;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    // Setters
    public void setCoordenadas(List<Double> coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
