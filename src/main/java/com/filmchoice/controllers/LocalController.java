package com.filmchoice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmchoice.dto.LocalDTOResponse;
import com.filmchoice.model.LocalInterface;
import com.filmchoice.utils.GerarMapa;
import com.filmchoice.utils.LocalCatalogo;

@RestController
@RequestMapping("/mapa")
public class LocalController {
    @GetMapping("/api/locais")
    public ResponseEntity<List<LocalDTOResponse>> listarLocais(){
        List<LocalDTOResponse> locais = GerarMapa.gerarPontosMapa();
        return ResponseEntity.ok(locais);
    }

    @GetMapping("/api/locais/{nome}")
    public ResponseEntity<LocalDTOResponse> getLocal(@PathVariable String nome){
        LocalInterface local = LocalCatalogo.fromNome(nome).getLocal();
        LocalDTOResponse localDTO = new LocalDTOResponse(local.getCoordenadas(), local.getNomeImagem(), local.getImagemUrl());
        return ResponseEntity.ok(localDTO);
    }
}



