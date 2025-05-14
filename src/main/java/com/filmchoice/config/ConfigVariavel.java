package com.filmchoice.config;


public class ConfigVariavel {
    private String dbUrl;
    private String dbUser;
    private String dbSenha;
    private String drive;
    private String dbPorta;
    private String dbName;
 

    private ConfigVariavel (Builder builder) {
        this.dbUrl = builder.url;
        this.dbUser = builder.user;
        this.dbSenha = builder.senha;
        this.drive = builder.drive;
        this.dbPorta = builder.porta;
        this.dbName = builder.name;
    } 

    public String getUrl (){
        return dbUrl;
    }

    public String getSenha (){
        return dbUrl;
    }

    public String getPorta(){
        return dbPorta;
    }

    public String getNameBd(){
        return dbName;
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
        private String porta;
        private String name;

        public Builder drive(String valueDrive){
            this.drive = valueDrive;
            return this;
        }
        
        public Builder url(String valueUrl, String especificacao){
            this.url = valueUrl;
            return this;
        }

        public Builder dbName(String valueDbName){
            this.name = valueDbName;
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

        public Builder porta(String valuePorta){
            this.porta = valuePorta;
            return this;
        }

        public ConfigVariavel build (){
            return new ConfigVariavel(this);
        }
    }
}