package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.response.PostagemResponse;
import br.com.cwi.berk.domain.Postagem;
import br.com.cwi.berk.mapper.PostagemMapper;
import br.com.cwi.berk.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarMinhasPostagensService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;


    public Page<PostagemResponse> listar(Pageable pageable) {
        Long idUsuarioAutenticado = usuarioAutenticadoService.getId();
        List<Postagem> todasMinhasPostagensList = postagemRepository.findAll().stream().filter(postagem -> postagem.getAutor().getId() == idUsuarioAutenticado)
                .collect(Collectors.toList());
        return new PageImpl<>(todasMinhasPostagensList).map(postagem -> PostagemMapper.toResponse(postagem));
    }

    public List<Postagem> listarEntity() {
        Long idUsuarioAutenticado = usuarioAutenticadoService.getId();
        return postagemRepository.findAll().stream().filter(postagem -> postagem.getAutor().getId() == idUsuarioAutenticado)
                .collect(Collectors.toList());
    }
}

