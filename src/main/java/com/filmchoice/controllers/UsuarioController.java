package com.filmchoice.controllers;

import java.io.IOException;
import java.util.List;
import com.filmchoice.entities.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    // GET ALL - Listar todos usuários
    @GetMapping
    public ResponseEntity<?> listarTodosUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.listarTodosUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponsee(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponsee(e.getMessage()));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarUsuarioPorEmail(@PathVariable String email) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
            return ResponseEntity.ok(usuario);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponsee(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTORecebido usuarioDTO) {
        try {
            // Remover campos que não devem ser atualizados via PUT
            usuarioDTO.setPapel(null); // Não permite alterar papel via PUT
            usuarioDTO.setCodigo(null); // Não permite alterar código via PUT

            Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioDTO);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponsee(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        try {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponsee(e.getMessage()));
        }
    }

    @GetMapping("/verificar-email/{email}")
    public ResponseEntity<?> verificarEmail(@PathVariable String email) {
        try {
            boolean existe = usuarioService.verificarUsuarioCadastrado(email);
            return ResponseEntity.ok(existe);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponsee(e.getMessage()));
        }
    }
}


