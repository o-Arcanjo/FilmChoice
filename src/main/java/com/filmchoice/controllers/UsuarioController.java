package com.filmchoice.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.filmchoice.dto.UsuarioDTO;
import com.filmchoice.dto.UsuarioDTORecebido;
import com.filmchoice.entities.Usuario;
import com.filmchoice.enums.Papel;
import com.filmchoice.response.ErrorResponsee;
import com.filmchoice.response.TokenResponse;
import com.filmchoice.services.ServiceException;
import com.filmchoice.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuários", description = "Operações de gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro/admin")
    @Operation(summary = "Cadastrar usuário administrador", 
               description = "Cadastra um usuário com papel de administrador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário administrador cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou erro no cadastro")
    })
    public ResponseEntity<String> criarUsuarioAdministrador(
            @Parameter(description = "Dados do usuário administrador") 
            @RequestBody UsuarioDTORecebido usuarioDTO) {
        try {
            UsuarioDTO usuarioDTOBuilder = UsuarioDTO.builder()
                    .nome(usuarioDTO.getNome())
                    .email(usuarioDTO.getEmail())
                    .senha(usuarioDTO.getSenha())
                    .codigo(usuarioDTO.getCodigo())
                    .papel(Papel.ADMIN)
                    .build();
            usuarioService.cadastrarUsuario(usuarioDTOBuilder);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário administrador cadastrado com sucesso!");
        } catch (ServiceException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastro")
    @Operation(summary = "Cadastrar usuário comum", 
               description = "Cadastra um usuário com papel de usuário comum")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário comum cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou erro no cadastro")
    })
    public ResponseEntity<String> criarUsuarioComum(
            @Parameter(description = "Dados do usuário comum") 
            @RequestBody UsuarioDTORecebido usuarioDTO) {
        try {
            UsuarioDTO usuarioDTOBuilder = UsuarioDTO.builder()
                    .nome(usuarioDTO.getNome())
                    .email(usuarioDTO.getEmail())
                    .senha(usuarioDTO.getSenha())
                    .papel(Papel.USER)
                    .build();
            usuarioService.cadastrarUsuario(usuarioDTOBuilder);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário comum cadastrado com sucesso!");
        } catch (ServiceException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuário", 
               description = "Realiza o login de um usuário, gerando um token de autenticação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<?> login(
            @Parameter(description = "Credenciais de login") 
            @RequestBody UsuarioDTORecebido usuarioDTO) {
        try {
            String token = usuarioService.login(usuarioDTO.getEmail(), usuarioDTO.getSenha());
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponsee(e.getMessage()));
        }
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os usuários", 
               description = "Retorna uma lista com todos os usuários cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado")
    })
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
    @Operation(summary = "Buscar usuário por ID", 
               description = "Retorna um usuário específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<?> buscarUsuarioPorId(
            @Parameter(description = "ID do usuário") 
            @PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorId(id);
            Usuario usuarioFormatado = new Usuario(usuario.getNome(),null, usuario.getEmail(), null, null);
            return ResponseEntity.ok(usuarioFormatado);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponsee(e.getMessage()));
        }
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar usuário por email", 
               description = "Retorna um usuário específico pelo seu email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<?> buscarUsuarioPorEmail(
            @Parameter(description = "Email do usuário") 
            @PathVariable String email) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
            return ResponseEntity.ok(usuario);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponsee(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", 
               description = "Atualiza os dados de um usuário existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<?> atualizarUsuario(
            @Parameter(description = "ID do usuário") 
            @PathVariable Long id,
            @Parameter(description = "Novos dados do usuário") 
            @RequestBody UsuarioDTORecebido usuarioDTO) {
        try {
            // Remover campos que não devem ser atualizados via PUT
            usuarioDTO.setEmail(null);
            usuarioDTO.setPapel(null); 
            usuarioDTO.setCodigo(null); // Não permite alterar código via PUT

            Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioDTO);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponsee(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário", 
               description = "Remove um usuário do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<?> deletarUsuario(
            @Parameter(description = "ID do usuário") 
            @PathVariable Long id) {
        try {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponsee(e.getMessage()));
        }
    }

    @GetMapping("/verificar-email/{email}")
    @Operation(summary = "Verificar email", 
               description = "Verifica se um email já está cadastrado no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Verificação realizada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<?> verificarEmail(
            @Parameter(description = "Email a ser verificado") 
            @PathVariable String email) {
        try {
            boolean existe = usuarioService.verificarUsuarioCadastrado(email);
            return ResponseEntity.ok(existe);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponsee(e.getMessage()));
        }
    }
}