    package com.filmchoice.dto;

    import com.filmchoice.entities.Usuario;

    public class AvaliacaoDTO {
        private Long id; // NOVO
        private FilmeDTO filme;
        private UsuarioDTO user;
        private String comentario;
        private Integer nota;

        private AvaliacaoDTO(Builder builder) {
            this.id = builder.id;
            this.filme = builder.filme;
            this.user = builder.user;
            this.comentario = builder.comentario;
            this.nota = builder.nota;
        }

        // Getters
        public Long getId() {
            return id;
        }

        public FilmeDTO getFilme() {
            return filme;
        }

        public Long getFilmeId(){
            return filme.getId();
        }

        public Long getUsuarioId(){
            return user.getId();
        }

        public UsuarioDTO getUser() {
            return user;
        }

        public String getComentario() {
            return comentario;
        }

        public Integer getNota() {
            return nota;
        }

        // Builder
        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private Long id; 
            private FilmeDTO filme;
            private UsuarioDTO user;
            private String comentario;
            private Integer nota;

            public Builder id(Long id) {
                this.id = id;
                return this;
            }

            public Builder filme(FilmeDTO filmeId) {
                this.filme = filmeId;
                return this;
            }

            public Builder usuario(UsuarioDTO userId) {
                this.user = userId;
                return this;
            }

            public Builder nota(Integer nota) {
                this.nota = nota;
                return this;
            }

            public Builder comentario(String comentario) {
                this.comentario = comentario;
                return this;
            }

            public AvaliacaoDTO build() {
                return new AvaliacaoDTO(this);
            }
        }
    }
