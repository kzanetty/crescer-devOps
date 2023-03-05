package br.com.cwi.berk.service;

import br.com.cwi.berk.domain.Amigo;
import br.com.cwi.berk.domain.enums.AmizadeStatus;
import br.com.cwi.berk.repository.AmigoRepository;
import br.com.cwi.berk.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DesfazerAmizadeService {

    @Autowired
    private AmigoRepository amigoRepository;

    @Autowired
    private BuscarSolicitacaoDeAmizadeService buscarSolicitacaoDeAmizadeService;

    public void desfazer(Long id) {
        Amigo amigo = buscarSolicitacaoDeAmizadeService.porId(id);

        if(amigo.getStatus() != AmizadeStatus.ACEITO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Essa amizade não existe.");
        }

        amigo.setStatus(AmizadeStatus.NEGADO);
        amigoRepository.save(amigo);
    }
}
