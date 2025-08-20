package com.filmchoice.services;

import com.filmchoice.dao.*;
import com.filmchoice.dto.*;
import com.filmchoice.entities.*;
import com.filmchoice.mapper.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioAdminServiceImpl implements UsuarioAdminService {

    private final FilmeDAO filmeDAO;
    private final AtorDAO atorDAO;
    private final DiretorDAO diretorDAO;
    private final GeneroDAO generoDAO;
    private final IdiomaDAO idiomaDAO;
    private final PaisDAO paisDAO;

    private final FilmeMapper filmeMapper;
    private final AtorMapper atorMapper;
    private final DiretorMapper diretorMapper;
    private final GeneroMapper generoMapper;
    private final IdiomaMapper idiomaMapper;
    private final PaisMapper paisMapper;

    @Autowired
    public UsuarioAdminServiceImpl(
            FilmeDAO filmeDAO,
            AtorDAO atorDAO,
            DiretorDAO diretorDAO,
            GeneroDAO generoDAO,
            IdiomaDAO idiomaDAO,
            PaisDAO paisDAO,
            FilmeMapper filmeMapper,
            AtorMapper atorMapper,
            DiretorMapper diretorMapper,
            GeneroMapper generoMapper,
            IdiomaMapper idiomaMapper,
            PaisMapper paisMapper) {
        this.filmeDAO = filmeDAO;
        this.atorDAO = atorDAO;
        this.diretorDAO = diretorDAO;
        this.generoDAO = generoDAO;
        this.idiomaDAO = idiomaDAO;
        this.paisDAO = paisDAO;
        this.filmeMapper = filmeMapper;
        this.atorMapper = atorMapper;
        this.diretorMapper = diretorMapper;
        this.generoMapper = generoMapper;
        this.idiomaMapper = idiomaMapper;
        this.paisMapper = paisMapper;
    }

    // ================== FILME ==================
    @Override
    @Transactional
    public void cadastrarFilme(FilmeDTO filmeDTO, String token) throws ServiceException, PersistenciaDawException {
        Filme filme = filmeMapper.converterElementoEntidade(filmeDTO);
        filmeDAO.save(filme);
    }

    @Override
    public List<FilmeDTO> listarFilmesCadastrados(String token) throws ServiceException, PersistenciaDawException {
        return filmeDAO.getAll().stream()
                .map(filmeMapper::converterElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void atualizarFilme(FilmeDTO filmeDTO, String token) throws ServiceException, PersistenciaDawException {
        Filme filmeExistente = filmeDAO.getByID(filmeDTO.getId());
        if (filmeExistente == null) {
            throw new ServiceException("Filme não encontrado");
        }
    
        Filme filmeAtualizado = filmeMapper.converterElementoEntidade(filmeDTO);
        filmeExistente.setTitulo(filmeAtualizado.getTitulo());
        filmeExistente.setLancamento(filmeAtualizado.getLancamento());
        filmeExistente.setDuracaoMinutos(filmeAtualizado.getDuracaoMinutos());
        filmeExistente.setReceita(filmeAtualizado.getReceita());
    
        filmeDAO.update(filmeExistente);
    }
    
    @Override
    @Transactional
    public void deletarFilme(Long id, String token) throws ServiceException, PersistenciaDawException {
        if (filmeDAO.getByID(id) == null) {
            throw new ServiceException("Filme não encontrado");
        }
        filmeDAO.delete(id);
    }

    @Override
    @Transactional
    public void cadastrarAtor(AtorDTO atorDTO, String token) throws ServiceException, PersistenciaDawException {
        Ator ator = atorMapper.converterElementoEntidade(atorDTO);
        atorDAO.save(ator);
    }

    @Override
    public List<AtorDTO> listarAtoresCadastrados(String token) throws ServiceException, PersistenciaDawException {
        return atorDAO.getAll().stream()
                .map(atorMapper::converterElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void atualizarAtor(AtorDTO atorDTO, String token) throws ServiceException, PersistenciaDawException {
        Ator atorExistente = atorDAO.getByID(atorDTO.getId());
        if (atorExistente == null) {
            throw new ServiceException("Ator não encontrado");
        }

        Ator atorAtualizado = atorMapper.converterElementoEntidade(atorDTO);
        atorExistente.setNome(atorAtualizado.getNome());
        atorExistente.setDataNascimento(atorAtualizado.getDataNascimento());

        atorDAO.update(atorExistente);
    }

    @Override
    @Transactional
    public void deletarAtor(Long id, String token) throws ServiceException, PersistenciaDawException {
        if (atorDAO.getByID(id) == null) {
            throw new ServiceException("Ator não encontrado");
        }
        atorDAO.delete(id);
    }

    @Override
    @Transactional
    public void cadastrarDiretor(DiretorDTO diretorDTO, String token) throws ServiceException, PersistenciaDawException {
        Diretor diretor = diretorMapper.converterElementoEntidade(diretorDTO);
        diretorDAO.save(diretor);
    }

    @Override
    public List<DiretorDTO> listarDiretoresCadastrados(String token) throws ServiceException, PersistenciaDawException {
        return diretorDAO.getAll().stream()
                .map(diretorMapper::converterElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void atualizarDiretor(DiretorDTO diretorDTO, String token) throws ServiceException, PersistenciaDawException {
        Diretor diretorExistente = diretorDAO.getByID(diretorDTO.getId());
        if (diretorExistente == null) {
            throw new ServiceException("Diretor não encontrado");
        }

        Diretor diretorAtualizado = diretorMapper.converterElementoEntidade(diretorDTO);
        diretorExistente.setNome(diretorAtualizado.getNome());
        diretorExistente.setDataNascimento(diretorAtualizado.getDataNascimento());

        diretorDAO.update(diretorExistente);
    }

    @Override
    @Transactional
    public void deletarDiretor(Long id, String token) throws ServiceException, PersistenciaDawException {
        if (diretorDAO.getByID(id) == null) {
            throw new ServiceException("Diretor não encontrado");
        }
        diretorDAO.delete(id);
    }

    @Override
    @Transactional
    public void cadastrarGenero(GeneroDTO generoDTO, String token) throws ServiceException, PersistenciaDawException {
        Genero genero = generoMapper.converterElementoEntidade(generoDTO);
        generoDAO.save(genero);
    }

    @Override
    public List<GeneroDTO> listarGenerosCadastrados(String token) throws ServiceException, PersistenciaDawException {
        return generoDAO.getAll().stream()
                .map(generoMapper::converterElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void atualizarGenero(GeneroDTO generoDTO, String token) throws ServiceException, PersistenciaDawException {
        Genero generoExistente = generoDAO.getByID(generoDTO.getId());
        if (generoExistente == null) {
            throw new ServiceException("Gênero não encontrado");
        }
        Genero generoAtualizado = generoMapper.converterElementoEntidade(generoDTO);
        generoExistente.setTipo(generoAtualizado.getTipo());
        generoDAO.update(generoExistente);
    }

    @Override
    @Transactional
    public void deletarGenero(Long id, String token) throws ServiceException, PersistenciaDawException {
        if (generoDAO.getByID(id) == null) {
            throw new ServiceException("Gênero não encontrado");
        }
        generoDAO.delete(id);
    }

    @Override
    @Transactional
    public void cadastrarIdioma(IdiomaDTO idiomaDTO, String token) throws ServiceException, PersistenciaDawException {
        Idioma idioma = idiomaMapper.converterElementoEntidade(idiomaDTO);
        idiomaDAO.save(idioma);
    }

    @Override
    public List<IdiomaDTO> listarIdiomasCadastrados(String token) throws ServiceException, PersistenciaDawException {
        return idiomaDAO.getAll().stream()
                .map(idiomaMapper::converterElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void atualizarIdioma(IdiomaDTO idiomaDTO, String token) throws ServiceException, PersistenciaDawException {
        Idioma idiomaExistente = idiomaDAO.getByID(idiomaDTO.getId());
        if (idiomaExistente == null) {
            throw new ServiceException("Idioma não encontrado");
        }
        Idioma idiomaAtualizado = idiomaMapper.converterElementoEntidade(idiomaDTO);
        idiomaExistente.setTipo(idiomaAtualizado.getTipo());
        idiomaDAO.update(idiomaExistente);
    }

    @Override
    @Transactional
    public void deletarIdioma(Long id, String token) throws ServiceException, PersistenciaDawException {
        if (idiomaDAO.getByID(id) == null) {
            throw new ServiceException("Idioma não encontrado");
        }
        idiomaDAO.delete(id);
    }

    // ================== PAÍS ==================
    @Override
    @Transactional
    public void cadastrarPais(PaisDTO paisDTO, String token) throws ServiceException, PersistenciaDawException {
        Pais pais = paisMapper.converterElementoEntidade(paisDTO);
        paisDAO.save(pais);
    }

    @Override
    public List<PaisDTO> listarPaisesCadastrados(String token) throws ServiceException, PersistenciaDawException {
        return paisDAO.getAll().stream()
                .map(paisMapper::converterElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void atualizarPais(PaisDTO paisDTO, String token) throws ServiceException, PersistenciaDawException {
        Pais paisExistente = paisDAO.getByID(paisDTO.getId());
        if (paisExistente == null) {
            throw new ServiceException("País não encontrado");
        }
        Pais paisAtualizado = paisMapper.converterElementoEntidade(paisDTO);
        paisExistente.setNome(paisAtualizado.getNome());
        paisDAO.update(paisExistente);
    }

    @Override
    @Transactional
    public void deletarPais(Long id, String token) throws ServiceException, PersistenciaDawException {
        if (paisDAO.getByID(id) == null) {
            throw new ServiceException("País não encontrado");
        }
        paisDAO.delete(id);
    }
}
