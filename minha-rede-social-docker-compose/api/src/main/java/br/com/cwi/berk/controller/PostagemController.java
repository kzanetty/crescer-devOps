package br.com.cwi.berk.controller;

import br.com.cwi.berk.controller.request.AlterarVisibilidadePostagemRequest;
import br.com.cwi.berk.controller.request.ComentarNaFotoRequest;
import br.com.cwi.berk.controller.request.IncluirPostagemRequest;
import br.com.cwi.berk.controller.response.PostagemResponse;
import br.com.cwi.berk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private ListarMinhasPostagensService listarMinhasPostagensService;

    @Autowired
    private ListarPostagensDeAmigosService listarPostagensDeAmigosService;

    @Autowired
    private IncluirPostagemService incluirPostagemService;

    @Autowired
    private ListarPostangesDeUsuarioService listarPostangesDeUsuarioService;

    @Autowired
    private AlterarVisibilidadePostagemService alterarVisibilidadePostagemService;

    @Autowired
    private ComentarNaPostagemService comentarNaPostagemService;

    @Autowired
    private CurtirPostagemService curtirPostagemService;

    @Autowired
    private DescurtirPostagemService descurtirPostagemService;

    @Autowired
    private ListarTimeLineService listarTimeLineService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<PostagemResponse> listarMinhasPostagens(Pageable pageable) {
        return listarMinhasPostagensService.listar(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostagemResponse> listarPostagensPorIdDeUsuario(@PathVariable Long id, Pageable pageable) {
        return listarPostangesDeUsuarioService.listar(id, pageable);
    }

    @GetMapping("/amigos")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostagemResponse> listarPostagensDeAmigos(Pageable pageable) {
        return listarPostagensDeAmigosService.listar(pageable);
    }

    @GetMapping("/timeline")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostagemResponse> listarTimeLine(Pageable pageable) {
        return listarTimeLineService.listar(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostagemResponse incluirPostagem(@Valid @RequestBody IncluirPostagemRequest request) {
        return incluirPostagemService.incluir(request);
    }

    @PostMapping("/visibilidade/{idPostagem}")
    public void modificarVisibilidadeDaPostagem(@PathVariable Long idPostagem,
                                                @Valid @RequestBody AlterarVisibilidadePostagemRequest request) {
        alterarVisibilidadePostagemService.alterar(idPostagem, request);
    }

    @PostMapping("/comentar/{idPostagem}")
    public void comentarNaPostagem(@Valid @PathVariable Long idPostagem, @Valid @RequestBody ComentarNaFotoRequest request) {
        comentarNaPostagemService.comentar(idPostagem, request);
    }

    @PostMapping("curtir/{idPostagem}")
    public void curtirPostagem(@PathVariable(name = "idPostagem") Long idPostagem) {
        curtirPostagemService.curtir(idPostagem);
    }

    @PostMapping("descurtir/{idPostagem}")
    public void descurtirPostagem(@PathVariable(name = "idPostagem") Long idPostagem) {
        descurtirPostagemService.descurtir(idPostagem);
    }

}
