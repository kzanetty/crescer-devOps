package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.response.AmigoResponse;
import br.com.cwi.berk.domain.Amigo;
import br.com.cwi.berk.domain.enums.AmizadeStatus;
import br.com.cwi.berk.mapper.AmigoMapper;
import br.com.cwi.berk.repository.AmigoRepository;
import br.com.cwi.berk.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnviarPedidoDeAmizadeService {

    @Autowired
    private AmigoRepository amigoRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public AmigoResponse enviar(Long idRecebedor) {
        Usuario usuarioAutenticado = usuarioAutenticadoService.get();

        Usuario recebedor = buscarUsuarioService.porId(idRecebedor);

        Amigo amigo = new Amigo();
        amigo.setSolicitante(usuarioAutenticado);
        amigo.setRecebedor(recebedor);
        amigo.setStatus(AmizadeStatus.PENDENTE);

        amigoRepository.save(amigo);

        return AmigoMapper.toResponse(amigo);
    }
}
