package br.com.cwi.berk.service;

import br.com.cwi.berk.factory.UsuarioFactory;
import br.com.cwi.berk.security.controller.response.UsuarioResponse;
import br.com.cwi.berk.security.domain.Usuario;
import br.com.cwi.berk.security.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarVikingsServiceTest {

    @InjectMocks
    private ListarVikingsService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve buscar e retornar todos usuarios")
    void deveRetornarTodosOsUsuarios() {

        Pageable pageable = PageRequest.of(0, 5);

        List<Usuario> usuarios = List.of(
                UsuarioFactory.get(),
                UsuarioFactory.get(),
                UsuarioFactory.get()
        );

        Page<Usuario> usuariosPaginado = new PageImpl<>(usuarios);

        when(usuarioRepository.findAll(pageable)).thenReturn(usuariosPaginado);

        Page<UsuarioResponse> response = tested.listar(pageable);

        verify(usuarioRepository).findAll(pageable);
        assertEquals(usuarios.size(), response.getSize());
        assertEquals(usuarios.get(0).getId(), response.getContent().get(0).getId());
        assertEquals(usuarios.get(1).getId(), response.getContent().get(1).getId());
        assertEquals(usuarios.get(2).getId(), response.getContent().get(2).getId());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não encontrar usuarios")
    void deveRetornarListaVazia() {

        Pageable pageable = PageRequest.of(0, 5);

        Page<Usuario> usuarioPaginado = new PageImpl<>(new ArrayList<>());

        when(usuarioRepository.findAll(pageable)).thenReturn(usuarioPaginado);

        Page<UsuarioResponse> response = tested.listar(pageable);

        verify(usuarioRepository).findAll(pageable);

        Assertions.assertNotNull(response);
        assertEquals(0, response.getSize());
    }
}
