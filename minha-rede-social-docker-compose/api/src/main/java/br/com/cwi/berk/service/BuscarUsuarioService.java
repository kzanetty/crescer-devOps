package br.com.cwi.berk.service;

import br.com.cwi.berk.security.domain.Usuario;
import br.com.cwi.berk.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BuscarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario porId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario não encontrado."));
    }
}
