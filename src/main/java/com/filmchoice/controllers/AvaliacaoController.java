package com.filmchoice.controllers;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dto.AvaliacaoDTO;
import com.filmchoice.dto.AvaliacaoDTORecebido;
import com.filmchoice.dto.FilmeDTO;
import com.filmchoice.services.ServiceException;
import com.filmchoice.services.proxies.UsuarioComumProxy;
import jakarta.servlet.http.*;



@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    private final UsuarioComumProxy usuarioComumProxy;
    
    public AvaliacaoController(UsuarioComumProxy usuarioComumProxy){
        this.usuarioComumProxy = usuarioComumProxy;
    }

    
 @PostMapping("/{filmeId}") 
public ResponseEntity<?> fazerAvaliacao(
        @PathVariable Long filmeId,
        @RequestBody AvaliacaoDTORecebido avaliacaoDTORecebido,
        HttpServletRequest request
) {
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
        // Exceção de serviço (ex: erro de negócio)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro ao processar avaliação: " + e.getMessage());
    } catch (PersistenciaDawException e) {
        // Exceção de persistência (erro no banco)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno ao salvar avaliação: " + e.getMessage());
    } catch (Exception e) {
        // Qualquer outra exceção não prevista
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro inesperado: " + e.getMessage());
    }
}


}
