package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.response.PostagemResponse;
import br.com.cwi.berk.domain.Postagem;
import br.com.cwi.berk.mapper.PostagemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarTimeLineService {

    @Autowired
    private ListarMinhasPostagensService listarMinhasPostagensService;

    @Autowired
    private ListarPostagensDeAmigosService listarPostagensDeAmigosService;


    public Page<PostagemResponse> listar(Pageable pageable) {
        List<Postagem> minhasPostagens = listarMinhasPostagensService.listarEntity();
        List<Postagem> postagemDeAmigos = listarPostagensDeAmigosService.listarEntity();

        List<Postagem> todasPostagens = new ArrayList<>();
        todasPostagens.addAll(minhasPostagens);
        todasPostagens.addAll(postagemDeAmigos);

        Collections.reverse(todasPostagens);
        return new PageImpl<>(todasPostagens.stream().map(postagem -> PostagemMapper.toResponse(postagem)).collect(Collectors.toList()));
    }
}
