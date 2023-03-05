package br.com.cwi.berk.service;

import br.com.cwi.berk.security.controller.response.UsuarioResponse;
import br.com.cwi.berk.security.mapper.UsuarioMapper;
import br.com.cwi.berk.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarVikingsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioResponse> listar(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(viking -> UsuarioMapper.toResponse(viking));
    }
}
