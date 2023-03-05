package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.request.IncluirPostagemRequest;
import br.com.cwi.berk.controller.response.PostagemResponse;
import br.com.cwi.berk.domain.Postagem;
import br.com.cwi.berk.mapper.PostagemMapper;
import br.com.cwi.berk.repository.PostagemRepository;
import br.com.cwi.berk.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IncluirPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public PostagemResponse incluir(IncluirPostagemRequest request) {
        Usuario usuario = buscarUsuarioService.porId(request.getIdUsuario());

        Postagem postagem = PostagemMapper.toEntity(request);
        postagem.setAutor(usuario);
        postagem.setDataCriacao(LocalDateTime.now());

        postagemRepository.save(postagem);

        PostagemResponse pr = PostagemMapper.toResponse(postagem);
        return pr;
    }
}
