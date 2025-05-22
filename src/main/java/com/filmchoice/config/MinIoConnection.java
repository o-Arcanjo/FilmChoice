package com.filmchoice.config;
import io.minio.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import com.filmchoice.enums.TipoConexao;


public class MinIoConnection implements IManagerConnection<MinioClient>{
    private static MinIoConnection instance;
    private MinioClient connection;

    private MinIoConnection(ConfigVariavel config) throws InvalidKeyException{
        if (config == null || config.getUrl() == null || config.getAccessKey() == null || config.getSecretAccessKey() == null) {
            throw new IllegalArgumentException("Configurações do MinIO não podem ser nulas");
        }

        this.connection = MinioClient.builder()
                            .endpoint(config.getUrl())
                            .credentials(config.getAccessKey(), config.getSecretAccessKey())
                            .build();
    }

    public static MinIoConnection getInstance() throws IOException, InvalidKeyException{
        if(instance == null){
            synchronized (MinIoConnection.class) {
                if(instance == null){
                    Properties props = LoadPropertiesBd.loadProperties(TipoConexao.MINIO);
                    ConfigVariavel config = ConfigVariavel.builder()
                                                          .url("DB_URL_MINIO")
                                                          .secretAccessKey("DB_SECRETACCESSKEY_MINIO")  
                                                          .accessKey("DB_ACCESSKEY_MINIO")
                                                          .build();

                    instance = new MinIoConnection(config);
                }
            }
        }
        return instance;
    }

    public boolean existeBucket(String nomeBucket) throws InvalidKeyException, IOException, NoSuchAlgorithmException{
        if(nomeBucket == null || nomeBucket.trim().isEmpty()){
            throw new IllegalArgumentException("Nome do bucket não pode ser vazio!");
        }
        try{
            return this.connection.bucketExists(BucketExistsArgs.builder().bucket(nomeBucket).build());
        }catch(Exception e){
            throw new IOException("Erro ao verificar existência do bucket: " + e.getMessage(), e);
        }
    }

    @Override
    public MinioClient getConexao(){
        return connection;
    }

}
