package com.filmchoice.dto;

public class GeneroDTO {
    private Long id;
    private String tipo;

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    private GeneroDTO(Builder builder) {
        this.id = builder.id;
        this.tipo = builder.tipo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String tipo;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public GeneroDTO build() {
            return new GeneroDTO(this);
        }
    }
}
