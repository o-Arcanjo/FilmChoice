package com.filmchoice.utils;
import java.util.List;

import com.filmchoice.model.LocalModel;
import com.filmchoice.model.LocalInterface;
import com.filmchoice.model.LocalProxy;

public enum LocalCatalogo {
    COLISEU(new LocalProxy(() -> LocalModel.builder()
                 .coordenadas(List.of(-23.56, -46.63))
                 .imagemUrl("https://s3.amazonaws.com/bucket/coliseu.png")
                 .nomeImagem("coliseu.png")
                 .build())),
    PRAIA(new LocalProxy(() -> LocalModel.builder()
                 .coordenadas(List.of(-22.90, -43.20))
                 .imagemUrl("https://s3.amazonaws.com/bucket/praia.png")
                 .nomeImagem("praia.png")
                 .build()));

    private final LocalInterface local;

    LocalCatalogo(LocalInterface local) {
        this.local = local;
    }

    public static LocalCatalogo fromNome(String nome) {
        for (LocalCatalogo local : values()) {
            if (local.name().equalsIgnoreCase(nome)) {
                return local;
            }
        }
        throw new IllegalArgumentException("Local não encontrado: " + nome);
    }
    

    public LocalInterface getLocal() {
        return local;
    }
}
