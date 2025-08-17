package com.filmchoice.enums;

public enum TokenEsperado {
    TOKEN("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]+$");

    private final String regex;

    TokenEsperado(String token){
        this.regex = token;
    }

    public String getRegex() {
        return regex;
    }
}
