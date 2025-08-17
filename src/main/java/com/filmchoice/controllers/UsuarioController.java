package com.filmchoice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.filmchoice.dto.UsuarioDTORecebido;
import com.filmchoice.entities.Usuario;
import com.filmchoice.response.*;
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
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDTORecebido usuarioDTO) {
        try{
             usuarioService.cadastrarUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
        }catch(ServiceException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }   
    }

     @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTORecebido usuarioDTO) {
        try {
            String token = usuarioService.login(usuarioDTO.getEmail(), usuarioDTO.getSenha());
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                               .body(new ErrorResponsee(e.getMessage()));
        }
    }
}


