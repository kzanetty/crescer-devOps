package br.com.cwi.berk.service;

import br.com.cwi.berk.security.config.UsuarioSecurity;
import br.com.cwi.berk.security.controller.response.UsuarioResponse;
import br.com.cwi.berk.security.domain.Usuario;
import br.com.cwi.berk.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static br.com.cwi.berk.security.mapper.UsuarioMapper.toResponse;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Service
public class UsuarioAutenticadoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Long getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UsuarioSecurity) {
            return ((UsuarioSecurity) authentication.getPrincipal()).getId();
        }
        return null;
    }

    public Usuario get() {
        Long id = getId();
        if (isNull(id)) {
            return null;
        }
        return usuarioRepository.findById(getId()).orElse(null);
    }

    public UsuarioResponse getResponse() {
        Usuario entity = get();
        return nonNull(entity) ? toResponse(entity) : new UsuarioResponse();
    }
}
