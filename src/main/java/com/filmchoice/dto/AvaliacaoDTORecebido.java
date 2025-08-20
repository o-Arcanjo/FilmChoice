package com.filmchoice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AvaliacaoDTORecebido {
    @NotNull(message = "O ID do usuário não pode ser nulo.")
    private Long userId;

    @NotBlank(message = "O comentário não pode ser nulo ou vazio.")
    private String comentario;

    @NotNull(message = "A nota não pode ser nula.")
    @Min(value = 0, message = "A nota mínima é 0.")
    @Max(value = 5, message = "A nota máxima é 5.")
    private Integer nota;

    // Construtor vazio
    public AvaliacaoDTORecebido() {}

    // Getters e Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
