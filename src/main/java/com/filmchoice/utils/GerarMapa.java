package com.filmchoice.utils;

import java.util.ArrayList;
import java.util.List;

import com.filmchoice.dto.LocalDTOResponse;
import com.filmchoice.model.LocalInterface;

public class GerarMapa {    
    public static List<LocalDTOResponse> gerarPontosMapa() {
        List<LocalDTOResponse> locaisDTO = new ArrayList<>();
        for (LocalCatalogo locEnum : LocalCatalogo.values()) {
            LocalInterface local = locEnum.getLocal(); 
            locaisDTO.add(new LocalDTOResponse(
                local.getCoordenadas(),
                local.getNomeImagem(),
                null 
            ));
        }

        return locaisDTO;
    }
}
