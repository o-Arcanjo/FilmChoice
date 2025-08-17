package com.filmchoice.services;

import com.filmchoice.dto.AtorDTO;
import com.filmchoice.dto.DiretorDTO;
import com.filmchoice.dto.FilmeDTO;
import com.filmchoice.dto.GeneroDTO;
import com.filmchoice.dto.IdiomaDTO;
import com.filmchoice.dto.PaisDTO;

import java.util.List;

/**
 * Define o contrato para as operações administrativas do sistema.
 * Todas as operações definidas nesta interface exigem autenticação e, portanto,
 * requerem a passagem de um token de autorização.
 */
public interface UsuarioAdminService {

    // --- OPERAÇÕES DE CADASTRO (CREATE) ---

    void cadastrarFilme(FilmeDTO filmeEntrada, String token) throws ServiceException;
    void cadastrarAtor(AtorDTO atorEntrada, String token) throws ServiceException;
    void cadastrarDiretor(DiretorDTO diretorEntrada, String token) throws ServiceException;
    void cadastrarGenero(GeneroDTO generoEntrada, String token) throws ServiceException;
    void cadastrarIdioma(IdiomaDTO idiomaEntrada, String token) throws ServiceException;
    void cadastrarPais(PaisDTO paisEntrada, String token) throws ServiceException;

    // --- OPERAÇÕES DE LISTAGEM (READ) ---

    List<FilmeDTO> listarFilmesCadastrados(String token) throws ServiceException;
    List<AtorDTO> listarAtoresCadastrados(String token) throws ServiceException;
    List<DiretorDTO> listarDiretoresCadastrados(String token) throws ServiceException;
    List<GeneroDTO> listarGenerosCadastrados(String token) throws ServiceException;
    List<IdiomaDTO> listarIdiomasCadastrados(String token) throws ServiceException;
    List<PaisDTO> listarPaisesCadastrados(String token) throws ServiceException;

    // --- OPERAÇÕES DE ATUALIZAÇÃO (UPDATE) ---

    void atualizarFilme(FilmeDTO filmeEntrada, String token) throws ServiceException;
    void atualizarAtor(AtorDTO atorEntrada, String token) throws ServiceException;
    void atualizarDiretor(DiretorDTO diretorEntrada, String token) throws ServiceException;
    void atualizarGenero(GeneroDTO generoEntrada, String token) throws ServiceException;
    void atualizarIdioma(IdiomaDTO idiomaEntrada, String token) throws ServiceException;
    void atualizarPais(PaisDTO paisEntrada, String token) throws ServiceException;

    // --- OPERAÇÕES DE EXCLUSÃO (DELETE) ---

    void deletarFilme(Long id, String token) throws ServiceException;
    void deletarAtor(Long id, String token) throws ServiceException;
    void deletarDiretor(Long id, String token) throws ServiceException;
    void deletarGenero(Long id, String token) throws ServiceException;
    void deletarIdioma(Long id, String token) throws ServiceException;
    void deletarPais(Long id, String token) throws ServiceException;
}
