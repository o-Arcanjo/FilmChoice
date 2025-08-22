package com.filmchoice.services;
import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dao.UsuarioDAO;
import com.filmchoice.dto.Payload;
import com.filmchoice.dto.UsuarioDTO;
import com.filmchoice.dto.UsuarioDTORecebido;
import com.filmchoice.entities.Usuario;
import com.filmchoice.enums.ChaveSecreta;
import com.filmchoice.mapper.impl.UsuarioMapper;
import java.util.List;
import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.filmchoice.config.LoadPropertiesBd;
import com.filmchoice.enums.CodigoAdmin;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final AuthService authService;
    private final TokenService tokenService;

    public UsuarioServiceImpl(UsuarioDAO usuarioDAO, UsuarioMapper usuarioMapper,
                              AuthService authServiceImpl, TokenService tokenService) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioMapper = usuarioMapper;
        this.authService = authServiceImpl;
        this.tokenService = tokenService;
    }

    @Override
    public boolean verificarUsuarioCadastrado(String email) throws ServiceException {
        try {
            return usuarioDAO.buscarPorEmail(email).isPresent();
        } catch (PersistenciaDawException e) {
            throw new ServiceException("Erro ao verificar usuário cadastrado", e);
        }
    }

    @Override
    public void cadastrarUsuario(UsuarioDTO usuarioDTORecebido) throws ServiceException, IOException {
        if (verificarUsuarioCadastrado(usuarioDTORecebido.getEmail())) {
            throw new ServiceException("Usuário já cadastrado com o e-mail: " + usuarioDTORecebido.getEmail());
        }

        Properties props = LoadPropertiesBd.loadProperties(CodigoAdmin.Codigo);
        String codigoSecreto = props.getProperty("CODIGO_ADMIN");


                        if (usuarioDTORecebido.getPapel() != null &&
                                usuarioDTORecebido.getPapel().name().equals("ADMIN") &&
                                !usuarioDTORecebido.getCodigo().equals(codigoSecreto)) {
                                    System.out.println(codigoSecreto);
                                    System.out.println(usuarioDTORecebido.getPapel().name());
                                    System.out.println(usuarioDTORecebido.getCodigo());
                            throw new ServiceException("Código de administrador inválido");
                        }
                        

        String senhaHash = authService.criptografarSenha(usuarioDTORecebido.getSenha());

        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .nome(usuarioDTORecebido.getNome())
                .email(usuarioDTORecebido.getEmail())
                .senha(senhaHash)
                .papel(usuarioDTORecebido.getPapel())
                .build();

        try {
            Usuario usuario = usuarioMapper.converterElementoEntidade(usuarioDTO);
            usuarioDAO.save(usuario);
        } catch (PersistenciaDawException e) {
            throw new ServiceException("Erro ao cadastrar usuário", e);
        }
    }

    @Override
    public String login(String email, String senha) throws ServiceException {
        try {
            Usuario usuario = usuarioDAO.buscarPorEmail(email)
                    .orElseThrow(() -> new PersistenciaDawException("Credenciais inválidas"));

            boolean senhaCorreta = authService.verificarSenha(senha, usuario.getSenha());

            if (!verificarUsuarioCadastrado(email) || !senhaCorreta) {
                throw new ServiceException("Credenciais inválidas");
            }

            Payload payload = Payload.builder()
                    .userId(usuario.getId())
                    .papel(usuario.getPapel())
                    .build();

            return tokenService.criarToken(payload, ChaveSecreta.TOKEN_JWT);

        } catch (PersistenciaDawException e) {
            throw new ServiceException("Erro ao autenticar usuário: " + e.getMessage(), e);
        } catch (ServiceException e) {
            throw e;
        }
    }

    // NOVOS MÉTODOS PARA CRUD COMPLETO
    @Override
    public Usuario buscarUsuarioPorId(Long id) throws ServiceException {
        try {
            return usuarioDAO.getByID(id);
        } catch (PersistenciaDawException e) {
            throw new ServiceException("Usuário não encontrado com ID: " + id, e);
        }
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) throws ServiceException {
        try {
            return usuarioDAO.buscarPorEmail(email)
                    .orElseThrow(() -> new ServiceException("Usuário não encontrado com email: " + email));
        } catch (PersistenciaDawException e) {
            throw new ServiceException("Erro ao buscar usuário por email: " + email, e);
        }
    }

    @Override
    public List<Usuario> listarTodosUsuarios() throws ServiceException {
        try {
            return usuarioDAO.getAll();
        } catch (PersistenciaDawException e) {
            throw new ServiceException("Erro ao listar todos os usuários", e);
        }
    }

    @Override
    public Usuario atualizarUsuario(Long id, UsuarioDTORecebido usuarioDTO) throws ServiceException {
        try {
            Usuario usuarioExistente = usuarioDAO.getByID(id);

            // Validar se novo email já existe (se foi alterado)
            if (usuarioDTO.getEmail() != null && !usuarioDTO.getEmail().equals(usuarioExistente.getEmail())) {
                if (verificarUsuarioCadastrado(usuarioDTO.getEmail())) {
                    throw new ServiceException("Email já está em uso por outro usuário");
                }
                usuarioExistente.setEmail(usuarioDTO.getEmail());
            }

            // Atualizar outros campos
            if (usuarioDTO.getNome() != null) {
                usuarioExistente.setNome(usuarioDTO.getNome());
            }

            if (usuarioDTO.getSenha() != null) {
                String senhaHash = authService.criptografarSenha(usuarioDTO.getSenha());
                usuarioExistente.setSenha(senhaHash);
            }

            // Papel não pode ser alterado via atualização comum
            // Código não é armazenado na entidade, apenas para validação

            return usuarioDAO.update(usuarioExistente);

        } catch (PersistenciaDawException e) {
            throw new ServiceException("Erro ao atualizar usuário com ID: " + id, e);
        }
    }

    @Override
    public void deletarUsuario(Long id) throws ServiceException {
        try {
            // Verificar se usuário existe antes de deletar
            usuarioDAO.getByID(id);
            usuarioDAO.delete(id);
        } catch (PersistenciaDawException e) {
            throw new ServiceException("Erro ao deletar usuário com ID: " + id, e);
        }
    }
}