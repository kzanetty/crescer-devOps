package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.response.AmigoResponse;
import br.com.cwi.berk.domain.Amigo;
import br.com.cwi.berk.factory.AmigoFactory;
import br.com.cwi.berk.factory.UsuarioFactory;
import br.com.cwi.berk.repository.AmigoRepository;
import br.com.cwi.berk.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarAmigosServiceTest {

    @InjectMocks
    private ListarAmigosService tested;

    @Mock
    private AmigoRepository amigoRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Test
    @DisplayName("Deve buscar e retornar todos amigos do usuario autenticado")
    void deveRetornarTodosAmigos() {

        Pageable pageable = PageRequest.of(0, 5);
        Usuario usuario = UsuarioFactory.get();

        List<Amigo> amigos = List.of(
                AmigoFactory.get(),
                AmigoFactory.get(),
                AmigoFactory.get()
        );

        Page<Amigo> amigosPaginado = new PageImpl<>(amigos);

        when(amigoRepository.findAll()).thenReturn(amigos);
        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        Page<AmigoResponse> response = tested.listarPaginado(pageable);

        verify(amigoRepository).findAll();
        verify(usuarioAutenticadoService).get();
    }

    @Test
    @DisplayName("Deve buscar e retornar lista vazia se não tiver amigos")
    void deveRetornarListaVazia() {

        Pageable pageable = PageRequest.of(0, 5);
        Usuario usuario = UsuarioFactory.get();

        List<Amigo> amigos = List.of(
                AmigoFactory.get()
        );

        Page<Amigo> amigosPaginado = new PageImpl<>(amigos);

        when(amigoRepository.findAll()).thenReturn(amigos);
        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        Page<AmigoResponse> response = tested.listarPaginado(pageable);

        verify(amigoRepository).findAll();
        verify(usuarioAutenticadoService).get();
    }
}
