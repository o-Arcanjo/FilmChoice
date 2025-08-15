package com.filmchoice.dto;

import java.time.LocalDate;

import com.filmchoice.enums.Papel;

public class Payload {
    private Long user_id;
    private Papel papel;
    private LocalDate periodoCriacao;


    private Payload(Builder builder){
        this.user_id = builder.user_id;
        this.papel = builder.papel;
        this.periodoCriacao = LocalDate.now();
    }


    public Long getUserId(){
        return user_id;
    }

    public Papel getPapel(){
        return papel;
    }

    public LocalDate getPeriodoCriacao(){
        return periodoCriacao;
    }

    public Builder builder(){
        return new Builder();
    }


    public static class Builder{
        private Long user_id;
        private Papel papel;
        
    
        public Builder userId(Long id){
            this.user_id = id;
            return this;
        }

        public Builder papel(Papel papel){
            this.papel = papel;
            return this;
        }


        public Payload build(){
            return new Payload(this);
        }
    }
}
