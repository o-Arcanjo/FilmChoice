package com.filmchoice.mapper.impl;
import org.springframework.stereotype.Component;

import com.filmchoice.dto.UsuarioDTO;
import com.filmchoice.entities.Usuario;
import com.filmchoice.mapper.Converter;


@Component
public class UsuarioMapper implements Converter<UsuarioDTO, Usuario> {

    @Override
    public UsuarioDTO converterElementoDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .papel(usuario.getPapel())
                .senha(usuario.getSenha())
                .build();
    }

    @Override
    public Usuario converterElementoEntidade(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario(); 
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setPapel(usuarioDTO.getPapel());
        return usuario;
    }

    @Override
    public UsuarioDTO toFullDTO(Usuario elemento) {
        return converterElementoDTO(elemento);
    }
    

}