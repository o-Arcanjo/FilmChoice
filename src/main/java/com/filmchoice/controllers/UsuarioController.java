package com.filmchoice.controllers;

import java.io.IOException;
import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmchoice.config.LoadPropertiesBd;
import com.filmchoice.dto.UsuarioDTORecebido;
import com.filmchoice.enums.Papel;
import com.filmchoice.response.ErrorResponsee;
import com.filmchoice.response.TokenResponse;
import com.filmchoice.services.ServiceException;
import com.filmchoice.services.UsuarioService;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro/admin")
    public ResponseEntity<String> criarUsuarioAdministrador(@RequestBody UsuarioDTORecebido usuarioDTO) {
        try{  
             usuarioDTO.setPapel(Papel.ADMIN);
             usuarioService.cadastrarUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário administrador cadastrado com sucesso!");
        }catch(ServiceException | IOException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }   
    }


    @PostMapping("/cadastro/user")
    public ResponseEntity<String> criarUsuarioComum(@RequestBody UsuarioDTORecebido usuarioDTO) {
        try{  
             usuarioDTO.setPapel(Papel.USER);
             usuarioService.cadastrarUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário comum cadastrado com sucesso!");
        }catch(ServiceException | IOException e){
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


