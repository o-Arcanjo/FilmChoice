package com.filmchoice.mapper.impl;
import com.filmchoice.dto.UsuarioDTO;
import com.filmchoice.entities.Usuario;
import com.filmchoice.mapper.Converter;

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
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .papel(usuarioDTO.getPapel())
                .senha(usuarioDTO.getSenha())
                .build();
    }
}