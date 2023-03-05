package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.response.AmigoResponse;
import br.com.cwi.berk.controller.response.PostagemResponse;
import br.com.cwi.berk.domain.Postagem;
import br.com.cwi.berk.mapper.PostagemMapper;
import br.com.cwi.berk.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarPostagensDeAmigosService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ListarAmigosService listarAmigosService;

    public Page<PostagemResponse> listar(Pageable pageable) {
        Long idUusuarioAutenticado = usuarioAutenticadoService.getId();
        List<Long> idAmigos = idDeAmigos(listarAmigosService.listar(), idUusuarioAutenticado);

        List<Postagem> todasPostagensList = postagemRepository.findAll();

        List<Postagem> listaFiltradaCompostagemDeAmigos = listFiltrada(idAmigos, todasPostagensList);

        return new PageImpl<>(listaFiltradaCompostagemDeAmigos.stream().map(postagem -> PostagemMapper.toResponse(postagem)).collect(Collectors.toList()));
    }

    public List<Postagem> listarEntity() {
        Long idUusuarioAutenticado = usuarioAutenticadoService.getId();
        List<Long> idAmigos = idDeAmigos(listarAmigosService.listar(), idUusuarioAutenticado);

        List<Postagem> todasPostagensList = postagemRepository.findAll();

        return listFiltrada(idAmigos, todasPostagensList);
    }

    private List<Long> idDeAmigos(List<AmigoResponse> listaDeAmigos, Long idUsuarioAutenticado) {
        List<Long> idDeAmigos = new ArrayList<>();
        for (AmigoResponse amigo : listaDeAmigos) {
            if(amigo.getSolicitante().getId() != idUsuarioAutenticado) {
                idDeAmigos.add(amigo.getSolicitante().getId());
            } else {
                idDeAmigos.add(amigo.getRecebedor().getId());
            }
        }
        return idDeAmigos;
    }

    private List<Postagem> listFiltrada(List<Long> idDeAmigos, List<Postagem> todasPostagensList) {
        List<Postagem> listaFinal = new ArrayList<>();
        todasPostagensList.stream().forEach(postagem -> {
            for(Long id : idDeAmigos) {
                if(postagem.getAutor().getId() == id) {
                    listaFinal.add(postagem);
                    break;
                }
            }
        } );
        return listaFinal;
    }
}
