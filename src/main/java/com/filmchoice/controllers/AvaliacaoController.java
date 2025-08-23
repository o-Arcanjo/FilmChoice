package com.filmchoice.controllers;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dto.AvaliacaoDTO;
import com.filmchoice.dto.AvaliacaoDTORecebido;
import com.filmchoice.dto.FilmeDTO;
import com.filmchoice.entities.Avaliacao;
import com.filmchoice.services.ServiceException;
import com.filmchoice.services.proxies.UsuarioComumProxy;
import com.filmchoice.services.proxies.AvaliacaoComumProxy;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final UsuarioComumProxy usuarioComumProxy;
    private final AvaliacaoComumProxy avaliacaoComumProxy;

    public AvaliacaoController(UsuarioComumProxy usuarioComumProxy, AvaliacaoComumProxy avaliacaoComumProxy) {
        this.usuarioComumProxy = usuarioComumProxy;
        this.avaliacaoComumProxy = avaliacaoComumProxy;
    }

    @GetMapping("/ranking-global")
    public ResponseEntity<?> obterRankingGlobal(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token ausente ou inválido");
        }

        String token = authHeader.substring(7);

        try {
            List<Avaliacao> ranking = avaliacaoComumProxy.obterRankingGlobal(token);
            return ResponseEntity.ok(ranking);
        } catch (PersistenciaDawException | ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao obter ranking global: " + e.getMessage());
        }
    }

    @PostMapping("/{filmeId}")
    public ResponseEntity<?> fazerAvaliacao(
            @PathVariable Long filmeId,
            @RequestBody AvaliacaoDTORecebido avaliacaoDTORecebido,
            HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token ausente ou inválido");
        }

        String token = authHeader.substring(7);

        AvaliacaoDTO avaliacao = AvaliacaoDTO.builder()
                .filme(FilmeDTO.builder().id(filmeId).build())
                .comentario(avaliacaoDTORecebido.getComentario())
                .nota(avaliacaoDTORecebido.getNota())
                .build();

        try {
            usuarioComumProxy.fazerAvaliacao(token, avaliacao);
            return ResponseEntity.ok("Avaliação feita com sucesso");
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao processar avaliação: " + e.getMessage());
        } catch (PersistenciaDawException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao salvar avaliação: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro inesperado: " + e.getMessage());
        }
    }
}
