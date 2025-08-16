package com.filmchoice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.filmchoice.dto.UsuarioDTORecebido;
import com.filmchoice.dto.UsuarioDTO;
import com.filmchoice.services.ServiceException;
import com.filmchoice.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDTORecebido usuarioDTORecebido) {
        try{
            UsuarioDTO usuarioCriado = usuarioService.cadastrarUsuario(usuarioDTORecebido);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
        }catch(ServiceException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }   
    }
    
    @GetMapping("/verificar")
    public ResponseEntity<Boolean> verificarUsuarioCadastrado(@RequestParam String email) {
        boolean existe = usuarioService.verificarUsuarioCadastrado(email);
        return ResponseEntity.ok(existe);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDTORecebido usuarioDTORecebido){
        String login = usuarioService.login(usuarioDTORecebido.getEmail(), usuarioDTORecebido.getSenha());
        return ResponseEntity.ok(token);
    }
}
