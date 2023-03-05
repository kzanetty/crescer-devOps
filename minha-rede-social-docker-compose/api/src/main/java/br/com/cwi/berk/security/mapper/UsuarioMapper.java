package br.com.cwi.berk.security.mapper;

import br.com.cwi.berk.security.controller.request.UsuarioRequest;
import br.com.cwi.berk.security.controller.response.UsuarioResponse;
import br.com.cwi.berk.security.domain.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setApelido(request.getApelido());
        entity.setDataNascimento(request.getDataNascimento());
        entity.setImageUrl(request.getImageUrl());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(entity.getId());
        response.setNome(entity.getNome());
        response.setEmail(entity.getEmail());
        response.setApelido(entity.getApelido());
        response.setDataNascimento(entity.getDataNascimento());
        response.setImageUrl(entity.getImageUrl());
        return response;
    }
}
