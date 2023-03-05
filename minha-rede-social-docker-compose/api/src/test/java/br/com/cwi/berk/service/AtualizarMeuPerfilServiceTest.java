package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.request.AtualizarMeuPerfilRequest;
import br.com.cwi.berk.factory.UsuarioFactory;
import br.com.cwi.berk.security.domain.Usuario;
import br.com.cwi.berk.security.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AtualizarMeuPerfilServiceTest {

    @InjectMocks
    private AtualizarMeuPerfilService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Test
    @DisplayName("Deve atualizar nome, apelido e imagem do usuario")
    void deveAtualizarDadosDoUsuario() {

        AtualizarMeuPerfilRequest request = new AtualizarMeuPerfilRequest();
        request.setApelido("Atualizando apelido");
        request.setImageUrl("Atualizando imagemUrl");
        request.setNome("Atualizando nome");
        Usuario usuario = UsuarioFactory.get();
        Long id = usuario.getId();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        tested.atualizar(request);

        verify(usuarioRepository).save(usuario);
        verify(usuarioAutenticadoService).get();

    }
}
