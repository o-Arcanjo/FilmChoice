package com.filmchoice.services.proxies;

import java.util.List;

import org.springframework.stereotype.Component;

import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dao.UsuarioDAO;
import com.filmchoice.dto.*;
import com.filmchoice.entities.Usuario;
import com.filmchoice.enums.ChaveSecreta;
import com.filmchoice.enums.Papel;
import com.filmchoice.services.AuthService;
import com.filmchoice.services.ServiceException;
import com.filmchoice.services.TokenService;
import com.filmchoice.services.UsuarioAdminService;


@Component
public class UsuarioAdminProxy implements UsuarioAdminService {

    private final TokenService tokenService;
    private final AuthService authService;
    private final UsuarioAdminService servicoReal;
    private final UsuarioDAO usuarioDAO;

    public UsuarioAdminProxy(UsuarioDAO usuarioDAO, TokenService tokenService, AuthService authService, UsuarioAdminService servicoReal) {
        this.tokenService = tokenService;
        this.authService = authService;
        this.servicoReal = servicoReal;
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * Método privado para autenticação e autorização.
     * @param token O token a ser validado.
     * @throws ServiceException se a autenticação ou autorização falhar.
     * @throws PersistenciaDawException se ocorrer um erro na camada de acesso a dados.
     */
    private void autenticarEAutorizar(String token) throws ServiceException, PersistenciaDawException {
        try {
            Payload payload = tokenService.validarToken(token, ChaveSecreta.TOKEN_JWT);
            if (payload == null) {
                throw new ServiceException("Token inválido ou payload não pôde ser extraído.");
            }

            Usuario usuario = usuarioDAO.getByID(payload.getUserId());
            if (usuario == null) {
                throw new ServiceException("Usuário associado ao token não foi encontrado.");
            }

            String papel = usuario.getPapel().name();
            if (!authService.autorizar(papel, Papel.ADMIN.name())) {
                throw new ServiceException("Acesso não autorizado. Permissão de Administrador necessária.");
            }
        } catch (PersistenciaDawException e) {
            throw e; 
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Ocorreu um erro inesperado durante a verificação de permissões.", e);
        }
    }

    // --- OPERAÇÕES DE CADASTRO (CREATE) ---

    @Override
    public void cadastrarFilme(FilmeDTO filmeEntrada, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.cadastrarFilme(filmeEntrada, token);
    }

    @Override
    public void cadastrarAtor(AtorDTO atorEntrada, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.cadastrarAtor(atorEntrada, token);
    }

    @Override
    public void cadastrarDiretor(DiretorDTO diretorEntrada, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.cadastrarDiretor(diretorEntrada, token);
    }

    @Override
    public void cadastrarGenero(GeneroDTO generoEntrada, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.cadastrarGenero(generoEntrada, token);
    }

    @Override
    public void cadastrarIdioma(IdiomaDTO idiomaEntrada, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.cadastrarIdioma(idiomaEntrada, token);
    }

    @Override
    public void cadastrarPais(PaisDTO paisEntrada, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.cadastrarPais(paisEntrada, token);
    }

    // --- OPERAÇÕES DE LISTAGEM (READ) ---

    @Override
    public List<FilmeDTO> listarFilmesCadastrados(String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        return servicoReal.listarFilmesCadastrados(token);
    }

    @Override
    public List<AtorDTO> listarAtoresCadastrados(String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        return servicoReal.listarAtoresCadastrados(token);
    }

    @Override
    public List<DiretorDTO> listarDiretoresCadastrados(String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        return servicoReal.listarDiretoresCadastrados(token);
    }

    @Override
    public List<GeneroDTO> listarGenerosCadastrados(String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        return servicoReal.listarGenerosCadastrados(token);
    }

    @Override
    public List<IdiomaDTO> listarIdiomasCadastrados(String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        return servicoReal.listarIdiomasCadastrados(token);
    }

    @Override
    public List<PaisDTO> listarPaisesCadastrados(String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        return servicoReal.listarPaisesCadastrados(token);
    }

    // --- OPERAÇÕES DE ATUALIZAÇÃO (UPDATE) ---

    @Override
    public void atualizarFilme(FilmeDTO filmeEntrada, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.atualizarFilme(filmeEntrada, token);
    }

    @Override
    public void atualizarAtor(AtorDTO atorEntrada, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.atualizarAtor(atorEntrada, token);
    }

    @Override
    public void atualizarDiretor(DiretorDTO diretorEntrada, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.atualizarDiretor(diretorEntrada, token);
    }

    @Override
    public void atualizarGenero(GeneroDTO generoEntrada, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.atualizarGenero(generoEntrada, token);
    }

    @Override
    public void atualizarIdioma(IdiomaDTO idiomaEntrada, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.atualizarIdioma(idiomaEntrada, token);
    }

    @Override
    public void atualizarPais(PaisDTO paisEntrada, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.atualizarPais(paisEntrada, token);
    }

    // --- OPERAÇÕES DE EXCLUSÃO (DELETE) ---

    @Override
    public void deletarFilme(Long id, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.deletarFilme(id, token);
    }

    @Override
    public void deletarAtor(Long id, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.deletarAtor(id, token);
    }

    @Override
    public void deletarDiretor(Long id, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.deletarDiretor(id, token);
    }

    @Override
    public void deletarGenero(Long id, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.deletarGenero(id, token);
    }

    @Override
    public void deletarIdioma(Long id, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.deletarIdioma(id, token);
    }

    @Override
    public void deletarPais(Long id, String token) throws ServiceException, PersistenciaDawException {
        autenticarEAutorizar(token);
        servicoReal.deletarPais(id, token);
    }
}
