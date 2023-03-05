package br.com.cwi.berk.service;

import br.com.cwi.berk.domain.Postagem;
import br.com.cwi.berk.repository.PostagemRepository;
import br.com.cwi.berk.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescurtirPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private  UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarPostagemService buscarPostagemService;

    public void descurtir(Long idPostagem) {
        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        Postagem postagem = buscarPostagemService.porId(idPostagem);

        postagem.getCurtidas().remove(usuarioAutenticado);

        postagemRepository.save(postagem);
    }
}
