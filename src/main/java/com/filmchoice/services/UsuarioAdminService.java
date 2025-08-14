package com.filmchoice.services;

import com.filmchoice.dto.AtorDTO;
import com.filmchoice.dto.DiretorDTO;
import com.filmchoice.dto.FilmeDTO;
import com.filmchoice.dto.GeneroDTO;
import com.filmchoice.dto.IdiomaDTO;
import com.filmchoice.dto.PaisDTO;

import java.util.List;

public interface UsuarioAdminService {
    // --- CADASTRAR ---
    void cadastrarFilme(FilmeDTO filmeEntrada) throws ServiceException;
    void cadastrarAtor(AtorDTO atorEntrada) throws ServiceException;
    void cadastrarDiretor(DiretorDTO diretorEntrada) throws ServiceException;
    void cadastrarGenero(GeneroDTO generoEntrada) throws ServiceException;
    void cadastrarIdioma(IdiomaDTO idiomaEntrada) throws ServiceException;
    void cadastrarPais(PaisDTO paisEntrada) throws ServiceException;

    // --- LISTAR ---
    List<FilmeDTO> listarFilmesCadastrados() throws ServiceException;
    List<AtorDTO> listarAtoresCadastrados() throws ServiceException;
    List<DiretorDTO> listarDiretoresCadastrados() throws ServiceException;
    List<GeneroDTO> listarGenerosCadastrados() throws ServiceException;
    List<IdiomaDTO> listarIdiomasCadastrados() throws ServiceException;
    List<PaisDTO> listarPaisesCadastrados() throws ServiceException;

    // --- ATUALIZAR ---
    void atualizarFilme(FilmeDTO filmeEntrada) throws ServiceException;
    void atualizarAtor(AtorDTO atorEntrada) throws ServiceException;
    void atualizarDiretor(DiretorDTO diretorEntrada) throws ServiceException;
    void atualizarGenero(GeneroDTO generoEntrada) throws ServiceException;
    void atualizarIdioma(IdiomaDTO idiomaEntrada) throws ServiceException;
    void atualizarPais(PaisDTO paisEntrada) throws ServiceException;

    // --- DELETAR ---
    void deletarFilme(Long id) throws ServiceException;
    void deletarAtor(Long id) throws ServiceException;
    void deletarDiretor(Long id) throws ServiceException;
    void deletarGenero(Long id) throws ServiceException;
    void deletarIdioma(Long id) throws ServiceException;
    void deletarPais(Long id) throws ServiceException;
}
