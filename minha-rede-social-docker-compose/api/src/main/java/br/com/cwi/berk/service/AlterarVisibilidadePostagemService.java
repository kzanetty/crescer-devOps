package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.request.AlterarVisibilidadePostagemRequest;
import br.com.cwi.berk.domain.Postagem;
import br.com.cwi.berk.domain.enums.VisibilidadePublicacao;
import br.com.cwi.berk.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlterarVisibilidadePostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private BuscarPostagemService buscarPostagemService;

    public void alterar(Long idPostagem, AlterarVisibilidadePostagemRequest request) {
        Postagem postagem = buscarPostagemService.porId(idPostagem);

        if(request.getVisibilidade() != VisibilidadePublicacao.PRIVADO && request.getVisibilidade() != VisibilidadePublicacao.PUBLICO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você informou uma visibilidade incorreta.");
        }
        postagem.setVisibilidade(request.getVisibilidade());

        postagemRepository.save(postagem);
    }
}
