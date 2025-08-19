package com.filmchoice.model;
import java.util.List;

public class LocalModel implements LocalInterface{
    private final List<Double> coordenadas;
    private final String imagemUrl;
    private final String nomeImagem;

    private LocalModel(Builder builder) {
        this.coordenadas = builder.coordenadas;
        this.imagemUrl = builder.imagemUrl;
        this.nomeImagem = builder.nomeImagem;
    }

    public List<Double> getCoordenadas() {
        return coordenadas;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<Double> coordenadas;
        private String imagemUrl;
        private String nomeImagem;

        public Builder coordenadas(List<Double> coordenadas) {
            this.coordenadas = coordenadas;
            return this;
        }

        public Builder imagemUrl(String imagemUrl) {
            this.imagemUrl = imagemUrl;
            return this;
        }

        public Builder nomeImagem(String nomeImagem) {
            this.nomeImagem = nomeImagem;
            return this;
        }

        public LocalModel build() {
            return new LocalModel(this);
        }
    }

}
