package br.com.cwi.berk.controller;

import br.com.cwi.berk.controller.response.AmigoResponse;
import br.com.cwi.berk.security.controller.response.UsuarioResponse;
import br.com.cwi.berk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amigos")
public class AmigoController {

    @Autowired
    private ListarAmigosService listarAmigosService;

    @Autowired
    private ListarSolicitacoesPendentesService listarSolicitacoesPendentesService;

    @Autowired
    private ListarPedidosDeAmizadeService listarPedidosDeAmizadeService;

    @Autowired
    private EnviarPedidoDeAmizadeService enviarPedidoDeAmizadeService;

    @Autowired
    private AceitarPedidoDeAmizadeService aceitarPedidoDeAmizadeService;

    @Autowired
    private RejeitarPedidoDeAmizadeService rejeitarPedidoDeAmizadeService;

    @Autowired
    private EncontrarAmigosService encontrarAmigosService;

    @Autowired
    private DesfazerAmizadeService desfazerAmizadeService;

    @Autowired
    private ListarNaoAmigosService listarNaoAmigosService;

    @GetMapping()
    public Page<AmigoResponse> listarMeusAmigos(Pageable pageable) {
        return listarAmigosService.listarPaginado(pageable);
    }

    @GetMapping("/pendentes")
    public Page<AmigoResponse> listarSolicitacoesPendentes(Pageable pageable) {
        return listarSolicitacoesPendentesService.listarPaginado(pageable);
    }

    @GetMapping("/disponiveis")
    public Page<UsuarioResponse> listarTodosQueNaoSaoMeusAmigos(Pageable pageable) {
        return listarNaoAmigosService.listarTodosQueNaoSaoMeusAmigosPaginado(pageable);
    }

    @GetMapping("/pedidos")
    public List<AmigoResponse> listarPedidosDeAmizade() {
        return listarPedidosDeAmizadeService.listar();
    }

    @PostMapping("/enviar/{idRecebedor}")
    public AmigoResponse enviarSolicitacaoDeAmizade(@PathVariable("idRecebedor") Long idRecebedor) {
        return enviarPedidoDeAmizadeService.enviar(idRecebedor);
    }

    @PostMapping("/pedidos/aceitar/{id}")
    public void aceitarPedidoDeAmizadePorId(@PathVariable("id") Long id) {
        aceitarPedidoDeAmizadeService.aceitar(id);
    }

    @PostMapping("/pedidos/rejeitar/{id}")
    public void rejeitarPedidoDeAmizadePorId(@PathVariable("id") Long id) {
        rejeitarPedidoDeAmizadeService.rejeitar(id);
    }

    @PostMapping("/desfazer/{id}")
    public void desfazerAmizade(@PathVariable("id") Long id) {
        desfazerAmizadeService.desfazer(id);
    }

    @GetMapping("/pesquisar")
    public List<AmigoResponse> encontrarAmigosPorNomeOuEmail(@RequestParam(name = "text") String text) {
        return encontrarAmigosService.procurar(text);
    }
}
