package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.response.AmigoResponse;
import br.com.cwi.berk.mapper.AmigoMapper;
import br.com.cwi.berk.repository.AmigoRepository;
import br.com.cwi.berk.security.domain.Usuario;
import br.com.cwi.berk.security.mapper.UsuarioMapper;
import br.com.cwi.berk.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EncontrarAmigosService {

    @Autowired
    private ListarAmigosService listarAmigosService;

    public List<AmigoResponse> procurar(String text) {
        List<AmigoResponse> amigos = listarAmigosService.listar();

        return amigos.stream().
                filter(amigo -> amigo.getRecebedor().getNome().contains(text) ||
                        amigo.getRecebedor().getEmail().contains(text) || amigo.getSolicitante().getNome().contains(text) ||
                amigo.getSolicitante().getEmail().contains(text)).collect(Collectors.toList());
    }
}
