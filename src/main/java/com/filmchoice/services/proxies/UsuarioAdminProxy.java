package com.filmchoice.services.proxies;

import java.util.List;

import com.filmchoice.dto.AtorDTO;
import com.filmchoice.dto.DiretorDTO;
import com.filmchoice.dto.FilmeDTO;
import com.filmchoice.dto.GeneroDTO;
import com.filmchoice.dto.IdiomaDTO;
import com.filmchoice.dto.PaisDTO;
import com.filmchoice.services.AuthService;
import com.filmchoice.services.ServiceException;
import com.filmchoice.services.UsuarioAdminService;

/**
 * Proxy de segurança para o serviço administrativo.
 * Intercepta todas as chamadas para verificar a autenticação do usuário
 * antes de delegar a operação para a implementação real do serviço.
 */
public class UsuarioAdminProxy implements UsuarioAdminService {

    private final AuthService authService;
    private final UsuarioAdminService servicoReal; // O serviço real que executa a lógica de negócio

    /**
     * Constrói o Proxy.
     * @param authService O serviço de autenticação.
     * @param servicoReal A implementação concreta do serviço administrativo a ser protegida.
     */
    public UsuarioAdminProxy(AuthService authService, UsuarioAdminService servicoReal) {
        this.authService = authService;
        this.servicoReal = servicoReal;
    }

    /**
     * Método privado e centralizado para verificar a autenticação.
     * É chamado no início de cada método público.
     * @param token O token a ser validado.
     * @throws ServiceException se a autenticação falhar.
     */
    private void autenticarEAutorizar(String token) throws ServiceException {
        if (!authService.autenticar(token)) {
            // Mensagem de erro mais específica para o cliente.
            throw new ServiceException("Acesso não autorizado. Token inválido, expirado ou permissões insuficientes.");
        }
    }

    // --- OPERAÇÕES DE CADASTRO (CREATE) ---

    @Override
    public void cadastrarFilme(FilmeDTO filmeEntrada, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.cadastrarFilme(filmeEntrada, token);
    }

    @Override
    public void cadastrarAtor(AtorDTO atorEntrada, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.cadastrarAtor(atorEntrada, token);
    }

    @Override
    public void cadastrarDiretor(DiretorDTO diretorEntrada, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.cadastrarDiretor(diretorEntrada, token);
    }

    @Override
    public void cadastrarGenero(GeneroDTO generoEntrada, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.cadastrarGenero(generoEntrada, token);
    }

    @Override
    public void cadastrarIdioma(IdiomaDTO idiomaEntrada, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.cadastrarIdioma(idiomaEntrada, token);
    }

    @Override
    public void cadastrarPais(PaisDTO paisEntrada, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.cadastrarPais(paisEntrada, token);
    }

    // --- OPERAÇÕES DE LISTAGEM (READ) ---

    @Override
    public List<FilmeDTO> listarFilmesCadastrados(String token) throws ServiceException {
        autenticarEAutorizar(token);
        return servicoReal.listarFilmesCadastrados(token);
    }

    @Override
    public List<AtorDTO> listarAtoresCadastrados(String token) throws ServiceException {
        autenticarEAutorizar(token);
        return servicoReal.listarAtoresCadastrados(token);
    }

    @Override
    public List<DiretorDTO> listarDiretoresCadastrados(String token) throws ServiceException {
        autenticarEAutorizar(token);
        return servicoReal.listarDiretoresCadastrados(token);
    }

    @Override
    public List<GeneroDTO> listarGenerosCadastrados(String token) throws ServiceException {
        autenticarEAutorizar(token);
        return servicoReal.listarGenerosCadastrados(token);
    }

    @Override
    public List<IdiomaDTO> listarIdiomasCadastrados(String token) throws ServiceException {
        autenticarEAutorizar(token);
        return servicoReal.listarIdiomasCadastrados(token);
    }

    @Override
    public List<PaisDTO> listarPaisesCadastrados(String token) throws ServiceException {
        autenticarEAutorizar(token);
        return servicoReal.listarPaisesCadastrados(token);
    }

    // --- OPERAÇÕES DE ATUALIZAÇÃO (UPDATE) ---

    @Override
    public void atualizarFilme(FilmeDTO filmeEntrada, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.atualizarFilme(filmeEntrada, token);
    }

    @Override
    public void atualizarAtor(AtorDTO atorEntrada, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.atualizarAtor(atorEntrada, token);
    }

    @Override
    public void atualizarDiretor(DiretorDTO diretorEntrada, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.atualizarDiretor(diretorEntrada, token);
    }

    @Override
    public void atualizarGenero(GeneroDTO generoEntrada, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.atualizarGenero(generoEntrada, token);
    }

    @Override
    public void atualizarIdioma(IdiomaDTO idiomaEntrada, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.atualizarIdioma(idiomaEntrada, token);
    }

    @Override
    public void atualizarPais(PaisDTO paisEntrada, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.atualizarPais(paisEntrada, token);
    }

    // --- OPERAÇÕES DE EXCLUSÃO (DELETE) ---

    @Override
    public void deletarFilme(Long id, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.deletarFilme(id, token);
    }

    @Override
    public void deletarAtor(Long id, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.deletarAtor(id, token);
    }

    @Override
    public void deletarDiretor(Long id, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.deletarDiretor(id, token);
    }

    @Override
    public void deletarGenero(Long id, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.deletarGenero(id, token);
    }

    @Override
    public void deletarIdioma(Long id, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.deletarIdioma(id, token);
    }

    @Override
    public void deletarPais(Long id, String token) throws ServiceException {
        autenticarEAutorizar(token);
        servicoReal.deletarPais(id, token);
    }
}
