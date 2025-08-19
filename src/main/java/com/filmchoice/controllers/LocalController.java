package com.filmchoice.controllers;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dto.LocalDTOResponse;
import com.filmchoice.model.LocalInterface;
import com.filmchoice.utils.GerarMapa;
import com.filmchoice.utils.LocalCatalogo;
import com.filmchoice.services.LocalService;


@RestController
@RequestMapping("/mapa/api/locais")
public class LocalController {

    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    // Listar todos os locais
    @GetMapping
    public ResponseEntity<List<LocalDTOResponse>> listarLocais() throws PersistenciaDawException {
        List<LocalInterface> locais = localService.obterLocalizacoes();
        List<LocalDTOResponse> locaisDTO = locais.stream()
                .map(local -> new LocalDTOResponse(
                        local.getCoordenadas(),
                        local.getNomeImagem(),
                        local.getImagemUrl()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(locaisDTO);
    }

    // Obter local por nome
    @GetMapping("/{nome}")
    public ResponseEntity<LocalDTOResponse> getLocal(@PathVariable String nome) throws PersistenciaDawException {
        LocalInterface local = localService.obterLocalizacoes().stream()
                .filter(l -> l.getNomeImagem().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));

        LocalDTOResponse localDTO = new LocalDTOResponse(
                local.getCoordenadas(),
                local.getNomeImagem(),
                local.getImagemUrl()
        );
        return ResponseEntity.ok(localDTO);
    }

    // ✅ Listar todos os locais fixos
    @GetMapping("/fixo")
    public ResponseEntity<List<LocalDTOResponse>> listarLocaisFixos() {
        List<LocalDTOResponse> locais = GerarMapa.gerarPontosMapa(); // ou LocalCatalogo.getTodos()
        return ResponseEntity.ok(locais);
    }

    // ✅ Obter local fixo por nome
    @GetMapping("/fixo/{nome}")
    public ResponseEntity<LocalDTOResponse> getLocalFixo(@PathVariable String nome) {
        LocalInterface local = LocalCatalogo.fromNome(nome).getLocal();
        LocalDTOResponse localDTO = new LocalDTOResponse(
                local.getCoordenadas(),
                local.getNomeImagem(),
                local.getImagemUrl()
        );
        return ResponseEntity.ok(localDTO);
    }
}


