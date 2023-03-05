package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.response.PostagemResponse;
import br.com.cwi.berk.mapper.PostagemMapper;
import br.com.cwi.berk.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarPostangesDeUsuarioService {

    @Autowired
    private PostagemRepository postagemRepository;

    public Page<PostagemResponse> listar(Long id, Pageable pageable) {
        return postagemRepository.findAllByAutorId(id, pageable)
                .map(postagem -> PostagemMapper.toResponse(postagem));
    }
}
