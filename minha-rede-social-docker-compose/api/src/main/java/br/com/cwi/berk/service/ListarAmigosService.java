package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.response.AmigoResponse;
import br.com.cwi.berk.domain.Amigo;
import br.com.cwi.berk.domain.enums.AmizadeStatus;
import br.com.cwi.berk.mapper.AmigoMapper;
import br.com.cwi.berk.repository.AmigoRepository;
import br.com.cwi.berk.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarAmigosService {

    @Autowired
    private AmigoRepository amigoRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public List<AmigoResponse> listar() {
        List<Amigo> amigos = amigoRepository.findAll();
        Usuario usuario = usuarioAutenticadoService.get();

        return amigos.stream()
                .filter(amigo -> (amigo.getRecebedor().getId() == usuario.getId() || amigo.getSolicitante().getId() == usuario.getId()) && amigo.getStatus() == AmizadeStatus.ACEITO)
                .map(amigo -> AmigoMapper.toResponse(amigo)).collect(Collectors.toList());
    }

    public Page<AmigoResponse> listarPaginado(Pageable pageable) {
        List<Amigo> amigos = amigoRepository.findAll();
        Usuario usuario = usuarioAutenticadoService.get();

        List<AmigoResponse> response = amigos.stream()
                .filter(amigo -> (amigo.getRecebedor().getId() == usuario.getId() || amigo.getSolicitante().getId() == usuario.getId()) && amigo.getStatus() == AmizadeStatus.ACEITO)
                .map(amigo -> AmigoMapper.toResponse(amigo)).collect(Collectors.toList());

        return  new PageImpl<>(response);

    }
}
