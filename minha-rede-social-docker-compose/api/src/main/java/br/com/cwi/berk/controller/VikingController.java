package br.com.cwi.berk.controller;

import br.com.cwi.berk.controller.request.AtualizarMeuPerfilRequest;
import br.com.cwi.berk.security.controller.response.UsuarioResponse;
import br.com.cwi.berk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vikings")
public class VikingController {

    @Autowired
    private ListarVikingsService listarVikingsService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private AtualizarMeuPerfilService atualizarMeuPerfilService;

    @Autowired
    private EncontrarUsuariosPorNomeOuEmailService encontrarUsuariosPorNomeOuEmailService;

    @Autowired
    private DetalharUsuarioService detalharUsuarioService;

    @GetMapping("/testecompose")
    public String testeCompose() {
        return "funcionou";
    }

    @GetMapping
    public Page<UsuarioResponse> listarUsuariosPaginado(Pageable pageable) {
        return listarVikingsService.listar(pageable);
    }

    @GetMapping("/me")
    public UsuarioResponse exibirUsuarioAutenticado() {
        return usuarioAutenticadoService.getResponse();
    }

    @PostMapping("/editar")
    public void atualizarMeuPerfil(@Valid @RequestBody AtualizarMeuPerfilRequest request) {
        atualizarMeuPerfilService.atualizar(request);
    }

    @GetMapping("/pesquisar")
    public Page<UsuarioResponse> encontrarUsuariosPorNomeOuEmail(@RequestParam(name = "text") String text, Pageable pageable) {
        return encontrarUsuariosPorNomeOuEmailService.encontrarPorNomeOuEmail(text, pageable);
    }

    @GetMapping("/detalhar/{id}")
    public UsuarioResponse detalharUsuarioPorId(@PathVariable Long id) {
        return detalharUsuarioService.detalhar(id);
    }
}
