package com.filmchoice.response;

public class ErrorResponsee {
    private final String mensagem;

    public ErrorResponsee(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}