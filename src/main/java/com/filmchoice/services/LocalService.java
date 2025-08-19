package com.filmchoice.services;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.google.common.base.Supplier;
import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dao.impl.LocalDAO;
import com.filmchoice.entities.Local;
import com.filmchoice.model.LocalInterface;
import com.filmchoice.model.LocalModel;
import com.filmchoice.model.LocalProxy;


@Service
public class LocalService {
    private final LocalDAO localDAO;

    public LocalService(LocalDAO localDAO){
        this.localDAO = localDAO;
    }   

    public List<LocalInterface> obterLocalizacoes() throws PersistenciaDawException {
        List<Local> locais = localDAO.getAllLocais();

        return locais.stream()
                .map(localEntity -> {
                    Supplier<LocalModel> localSupplier = () -> LocalModel.builder()
                            .nomeImagem(localEntity.getNome())
                            .imagemUrl(localEntity.getImagemUrl())
                            .coordenadas(Arrays.asList(
                                    localEntity.getGeom().getY(), // latitude
                                    localEntity.getGeom().getX()  // longitude
                            ))
                            .build();

                    return new LocalProxy(localSupplier);
                })
                .collect(Collectors.toList());
    }
}
