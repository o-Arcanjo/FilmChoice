package com.filmchoice.config;


public class ConfigVariavel {
    private String dbUrl;
    private String dbUser;
    private String dbSenha;
    private String drive;
    private String dbPorta;
    private String dbHost;
    private String appName;
    private String database;
    private String secretAccessKey;
    private String accessKey;
 

    private ConfigVariavel (Builder builder) {
        this.dbUrl = builder.url;
        this.dbUser = builder.user;
        this.dbSenha = builder.senha;
        this.drive = builder.drive;
        this.dbPorta = builder.porta;
        this.dbHost = builder.dbHost;
        this.appName = builder.appName;
        this.database = builder.database;
        this.accessKey = builder.accessKey;
        this.secretAccessKey = builder.secretAccessKey;
    } 

    public String getUrl (){
        return dbUrl;
    }

    public String getAccessKey(){
        return accessKey;
    }

    public String getSecretAccessKey(){
        return secretAccessKey;
    }

    public String getSenha (){
        return dbSenha;
    }

    public String getPorta(){
        return dbPorta;
    }

    public String getHost(){
        return dbHost;
    }

    public String getUser (){
        return dbUser;
    }

    public String getDatabase(){
        return database;
    }
    
    public String getDrive(){
        return drive;
    }

    public String getAppName(){
        return appName;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {     
        private String url;
        private String user;
        private String senha;
        private String drive;
        private String porta;
        private String dbHost;
        private String appName;
        private String database;
        private String secretAccessKey;
        private String accessKey;

        public Builder drive(String valueDrive){
            this.drive = valueDrive;
            return this;
        }
        
        public Builder url(String valueUrl){
            this.url = valueUrl;
            return this;
        }

        public Builder secretAccessKey(String secretAccessKey){
            this.secretAccessKey = secretAccessKey;
            return this;
        }

        public Builder accessKey(String accessKey){
            this.accessKey = accessKey;
            return this;
        }

        public Builder dbHost(String valueDbHost){
            this.dbHost = valueDbHost;
            return this;
        }

        public Builder appName(String valueAppName){
            this.appName = valueAppName;
            return this;
        }

        public Builder database(String valueDatabase){
            this.database = valueDatabase;
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