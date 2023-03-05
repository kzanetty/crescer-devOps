package br.com.cwi.berk.service;

import br.com.cwi.berk.domain.Postagem;
import br.com.cwi.berk.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BuscarPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    public Postagem porId(Long idPostagem) {
        return postagemRepository.findById(idPostagem)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Postagem não encontrada"));
    }
}
