package com.filmchoice.middlewares;

public class ClasseBase {
    private String nomeClasse;
    private String currentVariavel;
    private String currentValor;

    private ClasseBase(Builder builder) {
        this.nomeClasse = builder.nomeClasse;
        this.currentVariavel = builder.currentVariavel;
        this.currentValor = builder.currentValor;
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public String getCurrentVariavel() {
        return currentVariavel;
    }

    public String getCurrentValor() {
        return currentValor;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private String nomeClasse;
        private String currentVariavel;
        private String currentValor;

        public Builder nomeClasse(String nomeClasse) {
            this.nomeClasse = nomeClasse;
            return this;
        }

        public Builder currentVariavel(String currentVariavel) {
            this.currentVariavel = currentVariavel;
            return this;
        }

        public Builder currentValor(String valor) {
            this.currentValor = valor;
            return this;
        }

        public ClasseBase build() {
            return new ClasseBase(this);
        }
    }
}
