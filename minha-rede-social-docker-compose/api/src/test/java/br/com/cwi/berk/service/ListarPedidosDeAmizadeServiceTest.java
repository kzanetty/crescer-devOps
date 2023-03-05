package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.response.AmigoResponse;
import br.com.cwi.berk.domain.Amigo;
import br.com.cwi.berk.domain.enums.AmizadeStatus;
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

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarPedidosDeAmizadeServiceTest {

    @InjectMocks
    private ListarPedidosDeAmizadeService tested;

    @Mock
    private AmigoRepository amigoRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Test
    @DisplayName("Deve listar todos pedidos de amizade pendentes")
    void deveListarTodosPedidosDeAmizade() {
        Usuario recebedor = UsuarioFactory.get();
        Usuario solicitante1 = UsuarioFactory.get();
        Usuario solicitante2 = UsuarioFactory.get();

        Amigo amigoUm = AmigoFactory.get();
        amigoUm.setSolicitante(solicitante1);
        amigoUm.setRecebedor(recebedor);
        amigoUm.setStatus(AmizadeStatus.ACEITO);

        Amigo amigoDois = AmigoFactory.get();
        amigoDois.setSolicitante(solicitante2);
        amigoDois.setRecebedor(recebedor);
        amigoDois.setStatus(AmizadeStatus.ACEITO);

        List<Amigo> amigos = List.of(
                amigoUm,
                amigoDois
        );

        when(amigoRepository.findAll()).thenReturn(amigos);
        when(usuarioAutenticadoService.get()).thenReturn(recebedor);

        List<AmigoResponse> response = tested.listar();

        verify(amigoRepository).findAll();
        verify(usuarioAutenticadoService).get();
    }

}
