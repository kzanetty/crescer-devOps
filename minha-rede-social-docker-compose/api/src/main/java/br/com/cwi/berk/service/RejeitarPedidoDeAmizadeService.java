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
public class RejeitarPedidoDeAmizadeService {

    @Autowired
    private AmigoRepository amigoRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarSolicitacaoDeAmizadeService buscarSolicitacaoDeAmizadeService;

    public void rejeitar(Long id) {
        Amigo amigo = buscarSolicitacaoDeAmizadeService.porId(id);
        Usuario usuario = usuarioAutenticadoService.get();

        if(amigo.getStatus() != AmizadeStatus.PENDENTE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esse pedido de amizade não esta como pendente.");
        }


        if(amigo.getRecebedor().getId() != usuario.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro... Esse pedido de amizade não é do seu usuario. ");
        }

        amigo.setStatus(AmizadeStatus.NEGADO);
        amigoRepository.save(amigo);
    }
}
