package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.response.AmigoResponse;
import br.com.cwi.berk.repository.AmigoRepository;
import br.com.cwi.berk.security.controller.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarNaoAmigosService {

    @Autowired
    private AmigoRepository amigoRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ListarVikingsService listarVikingsService;

    @Autowired
    private ListarAmigosService listarAmigosService;


    public Page<UsuarioResponse> listarTodosQueNaoSaoMeusAmigosPaginado(Pageable pageable) {
        Long idUsuarioAutenticado = usuarioAutenticadoService.getId();
        Page<UsuarioResponse> todosUsuarios = listarVikingsService.listar(pageable);
        List<Long> idDeAmigos = idDeAmigos(listarAmigosService.listar(), idUsuarioAutenticado);
        idDeAmigos.add(idUsuarioAutenticado);

        return new PageImpl<>(todosUsuarios.stream().filter(usuario -> !idDeAmigos.contains(usuario.getId())).collect(Collectors.toList()));
    }

    private List<Long> idDeAmigos(List<AmigoResponse> listaDeAmigos, Long idUsuarioAutenticado) {
        List<Long> idDeAmigos = new ArrayList<>();
        for (AmigoResponse amigo : listaDeAmigos) {
            if(amigo.getSolicitante().getId() != idUsuarioAutenticado) {
                idDeAmigos.add(amigo.getSolicitante().getId());
            } else {
                idDeAmigos.add(amigo.getRecebedor().getId());
            }
        }
        return idDeAmigos;
    }
}
