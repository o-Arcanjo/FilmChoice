package com.filmchoice.services;

import com.filmchoice.dao.PersistenciaDawException; // Importar a exceção
import com.filmchoice.dto.AtorDTO;
import com.filmchoice.dto.DiretorDTO;
import com.filmchoice.dto.FilmeDTO;
import com.filmchoice.dto.GeneroDTO;
import com.filmchoice.dto.IdiomaDTO;
import com.filmchoice.dto.PaisDTO;

import java.util.List;

/**
 * Define o contrato para as operações administrativas do sistema.
 * Todas as operações podem lançar exceções de serviço (lógica de negócio)
 * ou de persistência (acesso a dados).
 */
public interface UsuarioAdminService {

    // --- OPERAÇÕES DE CADASTRO (CREATE) ---

    void cadastrarFilme(FilmeDTO filmeEntrada, String token) throws ServiceException, PersistenciaDawException;
    void cadastrarAtor(AtorDTO atorEntrada, String token) throws ServiceException, PersistenciaDawException;
    void cadastrarDiretor(DiretorDTO diretorEntrada, String token) throws ServiceException, PersistenciaDawException;
    void cadastrarGenero(GeneroDTO generoEntrada, String token) throws ServiceException, PersistenciaDawException;
    void cadastrarIdioma(IdiomaDTO idiomaEntrada, String token) throws ServiceException, PersistenciaDawException;
    void cadastrarPais(PaisDTO paisEntrada, String token) throws ServiceException, PersistenciaDawException;

    // --- OPERAções DE LISTAGEM (READ) ---

    List<FilmeDTO> listarFilmesCadastrados(String token) throws ServiceException, PersistenciaDawException;
    List<AtorDTO> listarAtoresCadastrados(String token) throws ServiceException, PersistenciaDawException;
    List<DiretorDTO> listarDiretoresCadastrados(String token) throws ServiceException, PersistenciaDawException;
    List<GeneroDTO> listarGenerosCadastrados(String token) throws ServiceException, PersistenciaDawException;
    List<IdiomaDTO> listarIdiomasCadastrados(String token) throws ServiceException, PersistenciaDawException;
    List<PaisDTO> listarPaisesCadastrados(String token) throws ServiceException, PersistenciaDawException;

    // --- OPERAÇÕES DE ATUALIZAÇÃO (UPDATE) ---

    void atualizarFilme(FilmeDTO filmeEntrada, String token) throws ServiceException, PersistenciaDawException;
    void atualizarAtor(AtorDTO atorEntrada, String token) throws ServiceException, PersistenciaDawException;
    void atualizarDiretor(DiretorDTO diretorEntrada, String token) throws ServiceException, PersistenciaDawException;
    void atualizarGenero(GeneroDTO generoEntrada, String token) throws ServiceException, PersistenciaDawException;
    void atualizarIdioma(IdiomaDTO idiomaEntrada, String token) throws ServiceException, PersistenciaDawException;
    void atualizarPais(PaisDTO paisEntrada, String token) throws ServiceException, PersistenciaDawException;

    // --- OPERAÇÕES DE EXCLUSÃO (DELETE) ---

    void deletarFilme(Long id, String token) throws ServiceException, PersistenciaDawException;
    void deletarAtor(Long id, String token) throws ServiceException, PersistenciaDawException;
    void deletarDiretor(Long id, String token) throws ServiceException, PersistenciaDawException;
    void deletarGenero(Long id, String token) throws ServiceException, PersistenciaDawException;
    void deletarIdioma(Long id, String token) throws ServiceException, PersistenciaDawException;
    void deletarPais(Long id, String token) throws ServiceException, PersistenciaDawException;
}
