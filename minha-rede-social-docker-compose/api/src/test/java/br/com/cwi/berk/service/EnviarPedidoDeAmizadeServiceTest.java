package br.com.cwi.berk.service;

import br.com.cwi.berk.controller.response.AmigoResponse;
import br.com.cwi.berk.domain.Amigo;
import br.com.cwi.berk.domain.enums.AmizadeStatus;
import br.com.cwi.berk.factory.UsuarioFactory;
import br.com.cwi.berk.repository.AmigoRepository;
import br.com.cwi.berk.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnviarPedidoDeAmizadeServiceTest {

    @InjectMocks
    private EnviarPedidoDeAmizadeService tested;

    @Mock
    private AmigoRepository amigoRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarUsuarioService buscarUsuarioService;

    @Captor
    private ArgumentCaptor<Amigo> amigoCaptor;


    @Test
    @DisplayName("Deve enviar pedido de amizade")
    void deveEnviarPedidoDeAmizade() {
        Usuario recebedor = UsuarioFactory.get();
        Long idRecebedor = recebedor.getId();

        Usuario solicitante = UsuarioFactory.get();

        when(usuarioAutenticadoService.get()).thenReturn(solicitante);
        when(buscarUsuarioService.porId(idRecebedor)).thenReturn(recebedor);

        AmigoResponse response = tested.enviar(idRecebedor);

        verify(buscarUsuarioService).porId(idRecebedor);
        verify(usuarioAutenticadoService).get();
        verify(amigoRepository).save(amigoCaptor.capture());

        Amigo amigoCapt = amigoCaptor.getValue();

        assertEquals(recebedor, amigoCapt.getRecebedor());
        assertEquals(solicitante, amigoCapt.getSolicitante());
        assertEquals(AmizadeStatus.PENDENTE, amigoCapt.getStatus());
    }

}
