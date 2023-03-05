package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.request.AtualizarMeuPerfilRequest;
import br.com.cwi.berk.security.domain.Usuario;
import br.com.cwi.berk.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarMeuPerfilService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public void atualizar(AtualizarMeuPerfilRequest request) {
        Usuario usuario = usuarioAutenticadoService.get();

        usuario.setNome(request.getNome());
        usuario.setApelido(request.getApelido());
        usuario.setImageUrl(request.getImageUrl());

        usuarioRepository.save(usuario);
    }
}
