package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.request.ComentarNaFotoRequest;
import br.com.cwi.berk.domain.Comentario;
import br.com.cwi.berk.domain.Postagem;
import br.com.cwi.berk.repository.ComentarioRepository;
import br.com.cwi.berk.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarNaPostagemService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private BuscarPostagemService buscarPostagemService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public void comentar(Long idPostagem, ComentarNaFotoRequest request) {
        Postagem postagem = buscarPostagemService.porId(request.getIdPostagem());
        Usuario usuario = buscarUsuarioService.porId(request.getIdUsuario());
        Comentario comentario = new Comentario();
        comentario.setPostagem(postagem);
        comentario.setUsuario(usuario);
        comentario.setComentario(request.getComentario());

        comentarioRepository.save(comentario);
    }
}
