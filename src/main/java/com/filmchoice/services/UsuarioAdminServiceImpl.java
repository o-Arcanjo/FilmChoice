package com.filmchoice.services;

import com.filmchoice.dao.*;
import com.filmchoice.dto.*;
import com.filmchoice.exceptions.PersistenciaDawException;
import com.filmchoice.exceptions.ServiceException;
import com.filmchoice.mapper.*;
import com.filmchoice.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioAdminServiceImpl implements UsuarioAdminService {

    // Repositories
    private final FilmeRepository filmeRepository;
    private final AtorRepository atorRepository;
    private final DiretorRepository diretorRepository;
    private final GeneroRepository generoRepository;
    private final IdiomaRepository idiomaRepository;
    private final PaisRepository paisRepository;

    // Mappers
    private final FilmeMapper filmeMapper;
    private final AtorMapper atorMapper;
    private final DiretorMapper diretorMapper;
    private final GeneroMapper generoMapper;
    private final IdiomaMapper idiomaMapper;
    private final PaisMapper paisMapper;

    private final JwtTokenProvider tokenProvider;

    @Autowired
    public UsuarioAdminServiceImpl(
            FilmeRepository filmeRepository,
            AtorRepository atorRepository,
            DiretorRepository diretorRepository,
            GeneroRepository generoRepository,
            IdiomaRepository idiomaRepository,
            PaisRepository paisRepository,
            JwtTokenProvider tokenProvider,
            FilmeMapper filmeMapper,
            AtorMapper atorMapper,
            DiretorMapper diretorMapper,
            GeneroMapper generoMapper,
            IdiomaMapper idiomaMapper,
            PaisMapper paisMapper) {
        this.filmeRepository = filmeRepository;
        this.atorRepository = atorRepository;
        this.diretorRepository = diretorRepository;
        this.generoRepository = generoRepository;
        this.idiomaRepository = idiomaRepository;
        this.paisRepository = paisRepository;
        this.tokenProvider = tokenProvider;
        this.filmeMapper = filmeMapper;
        this.atorMapper = atorMapper;
        this.diretorMapper = diretorMapper;
        this.generoMapper = generoMapper;
        this.idiomaMapper = idiomaMapper;
        this.paisMapper = paisMapper;
    }

    private void validarTokenAdmin(String token) throws ServiceException {
        if (!tokenProvider.validateToken(token) || !tokenProvider.isAdmin(token)) {
            throw new ServiceException("Acesso não autorizado: requer privilégios de administrador");
        }
    }

    // ========== FILME ==========
    @Override
    @Transactional
    public void cadastrarFilme(FilmeDTO filmeDTO, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        Filme filme = filmeMapper.converterElementoEntidade(filmeDTO);
        filmeRepository.salvar(filme);
    }

    @Override
    public List<FilmeDTO> listarFilmesCadastrados(String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        return filmeRepository.listarTodos().stream()
                .map(filmeMapper::converterElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void atualizarFilme(FilmeDTO filmeDTO, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        Filme filme = filmeRepository.buscarPorId(filmeDTO.getId())
                .orElseThrow(() -> new ServiceException("Filme não encontrado"));
        filmeMapper.updateEntityFromDTO(filmeDTO, filme);
        filmeRepository.atualizar(filme);
    }

    @Override
    @Transactional
    public void deletarFilme(Long id, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        if (!filmeRepository.existe(id)) {
            throw new ServiceException("Filme não encontrado");
        }
        filmeRepository.deletar(id);
    }

    // ========== ATOR ==========
    @Override
    @Transactional
    public void cadastrarAtor(AtorDTO atorDTO, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        Ator ator = atorMapper.converterElementoEntidade(atorDTO);
        atorRepository.salvar(ator);
    }

    @Override
    public List<AtorDTO> listarAtoresCadastrados(String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        return atorRepository.listarTodos().stream()
                .map(atorMapper::converterElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void atualizarAtor(AtorDTO atorDTO, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        Ator ator = atorRepository.buscarPorId(atorDTO.getId())
                .orElseThrow(() -> new ServiceException("Ator não encontrado"));
        atorMapper.updateEntityFromDTO(atorDTO, ator);
        atorRepository.atualizar(ator);
    }

    @Override
    @Transactional
    public void deletarAtor(Long id, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        if (!atorRepository.existe(id)) {
            throw new ServiceException("Ator não encontrado");
        }
        atorRepository.deletar(id);
    }

    // ========== DIRETOR ==========
    @Override
    @Transactional
    public void cadastrarDiretor(DiretorDTO diretorDTO, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        Diretor diretor = diretorMapper.converterElementoEntidade(diretorDTO);
        diretorRepository.salvar(diretor);
    }

    @Override
    public List<DiretorDTO> listarDiretoresCadastrados(String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        return diretorRepository.listarTodos().stream()
                .map(diretorMapper::converterElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void atualizarDiretor(DiretorDTO diretorDTO, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        Diretor diretor = diretorRepository.buscarPorId(diretorDTO.getId())
                .orElseThrow(() -> new ServiceException("Diretor não encontrado"));
        diretorMapper.updateEntityFromDTO(diretorDTO, diretor);
        diretorRepository.atualizar(diretor);
    }

    @Override
    @Transactional
    public void deletarDiretor(Long id, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        if (!diretorRepository.existe(id)) {
            throw new ServiceException("Diretor não encontrado");
        }
        diretorRepository.deletar(id);
    }

    // ========== GÊNERO ==========
    @Override
    @Transactional
    public void cadastrarGenero(GeneroDTO generoDTO, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        Genero genero = generoMapper.converterElementoEntidade(generoDTO);
        generoRepository.salvar(genero);
    }

    @Override
    public List<GeneroDTO> listarGenerosCadastrados(String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        return generoRepository.listarTodos().stream()
                .map(generoMapper::converterElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void atualizarGenero(GeneroDTO generoDTO, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        Genero genero = generoRepository.buscarPorId(generoDTO.getId())
                .orElseThrow(() -> new ServiceException("Gênero não encontrado"));
        generoMapper.updateEntityFromDTO(generoDTO, genero);
        generoRepository.atualizar(genero);
    }

    @Override
    @Transactional
    public void deletarGenero(Long id, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        if (!generoRepository.existe(id)) {
            throw new ServiceException("Gênero não encontrado");
        }
        generoRepository.deletar(id);
    }

    // ========== IDIOMA ==========
    @Override
    @Transactional
    public void cadastrarIdioma(IdiomaDTO idiomaDTO, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        Idioma idioma = idiomaMapper.converterElementoEntidade(idiomaDTO);
        idiomaRepository.salvar(idioma);
    }

    @Override
    public List<IdiomaDTO> listarIdiomasCadastrados(String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        return idiomaRepository.listarTodos().stream()
                .map(idiomaMapper::converterElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void atualizarIdioma(IdiomaDTO idiomaDTO, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        Idioma idioma = idiomaRepository.buscarPorId(idiomaDTO.getId())
                .orElseThrow(() -> new ServiceException("Idioma não encontrado"));
        idiomaMapper.updateEntityFromDTO(idiomaDTO, idioma);
        idiomaRepository.atualizar(idioma);
    }

    @Override
    @Transactional
    public void deletarIdioma(Long id, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        if (!idiomaRepository.existe(id)) {
            throw new ServiceException("Idioma não encontrado");
        }
        idiomaRepository.deletar(id);
    }

    // ========== PAÍS ==========
    @Override
    @Transactional
    public void cadastrarPais(PaisDTO paisDTO, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        Pais pais = paisMapper.converterElementoEntidade(paisDTO);
        paisRepository.salvar(pais);
    }

    @Override
    public List<PaisDTO> listarPaisesCadastrados(String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        return paisRepository.listarTodos().stream()
                .map(paisMapper::converterElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void atualizarPais(PaisDTO paisDTO, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        Pais pais = paisRepository.buscarPorId(paisDTO.getId())
                .orElseThrow(() -> new ServiceException("País não encontrado"));
        paisMapper.updateEntityFromDTO(paisDTO, pais);
        paisRepository.atualizar(pais);
    }

    @Override
    @Transactional
    public void deletarPais(Long id, String token) throws ServiceException, PersistenciaDawException {
        validarTokenAdmin(token);
        if (!paisRepository.existe(id)) {
            throw new ServiceException("País não encontrado");
        }
        paisRepository.deletar(id);
    }
}