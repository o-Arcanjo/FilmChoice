package com.filmchoice.util;


public class ConfigVariavel {
    private String dbUrl;
    private String dbUser;
    private String dbSenha;
    private String drive;

    private ConfigVariavel (Builder builder) {
        this.dbUrl = builder.url;
        this.dbUser = builder.user;
        this.dbSenha = builder.senha;
        this.drive = builder.drive;
    } 

    public String getUrl (){
        return dbUrl;
    }

    public String getSenha (){
        return dbUrl;
    }

    public String getUser (){
        return dbUrl;
    }
    
    public String getDrive(){
        return drive;
    }

    public static class Builder {     
        private String url;
        private String user;
        private String senha;
        private String drive;

        public Builder drive(String valueDrive){
            this.drive = valueDrive;
            return this;
        }
        
        public Builder url(String valueUrl){
            this.url = valueUrl;
            return this;
        }

        public Builder user(String valueUser){
            this.user = valueUser;
            return this;
        }

        public Builder senha(String valueSenha){
            this.senha = valueSenha;
            return this;
        }

        public ConfigVariavel build (){
            return new ConfigVariavel(this);
        }
    }
}