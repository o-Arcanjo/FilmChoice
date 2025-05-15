package com.filmchoice.config;


public class ConfigVariavel {
    private String dbUrl;
    private String dbUser;
    private String dbSenha;
    private String drive;
    private String dbPorta;
    private String dbHost;
    private String appName;
 

    private ConfigVariavel (Builder builder) {
        this.dbUrl = builder.url;
        this.dbUser = builder.user;
        this.dbSenha = builder.senha;
        this.drive = builder.drive;
        this.dbPorta = builder.porta;
        this.dbHost = builder.dbHost;
        this.appName = builder.appName;
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

    public String getHost(){
        return dbHost;
    }

    public String getUser (){
        return dbUrl;
    }
    
    public String getDrive(){
        return drive;
    }

    public String getAppName(){
        return appName;
    }

    public static class Builder {     
        private String url;
        private String user;
        private String senha;
        private String drive;
        private String porta;
        private String dbHost;
        private String appName;

        public Builder drive(String valueDrive){
            this.drive = valueDrive;
            return this;
        }
        
        public Builder url(String valueUrl){
            this.url = valueUrl;
            return this;
        }

        public Builder dbHost(String valueDbHost){
            this.dbHost = valueDbHost;
            return this;
        }

        public Builder appName(String appName){
            this.appName = appName;
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