package br.com.cwi.berk.service;

import br.com.cwi.berk.domain.Amigo;
import br.com.cwi.berk.repository.AmigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BuscarSolicitacaoDeAmizadeService {

    @Autowired
    private AmigoRepository amigoRepository;

    public Amigo porId(Long id) {
        return amigoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido inexistente."));
    }
}
