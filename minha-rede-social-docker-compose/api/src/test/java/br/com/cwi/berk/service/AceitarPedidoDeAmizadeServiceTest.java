package br.com.cwi.berk.service;

import br.com.cwi.berk.domain.Amigo;
import br.com.cwi.berk.domain.enums.AmizadeStatus;
import br.com.cwi.berk.factory.AmigoFactory;
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
public class AceitarPedidoDeAmizadeServiceTest {

    @InjectMocks
    private AceitarPedidoDeAmizadeService tested;

    @Mock
    private AmigoRepository amigoRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarSolicitacaoDeAmizadeService buscarSolicitacaoDeAmizadeService;

    @Captor
    private ArgumentCaptor<Amigo> amigoCaptor;

    @Test
    @DisplayName("Deve aceitar o pedido de amizade")
    void deveAceitarPedidoDeAmizade() {
        Usuario Recebedor = UsuarioFactory.get();
        Usuario solicitante = UsuarioFactory.get();

        Amigo amigo = AmigoFactory.get();
        Long idAmigo = amigo.getId();
        amigo.setStatus(AmizadeStatus.PENDENTE);
        amigo.setSolicitante(solicitante);
        amigo.setRecebedor(Recebedor);

        when(buscarSolicitacaoDeAmizadeService.porId(idAmigo)).thenReturn(amigo);
        when(usuarioAutenticadoService.get()).thenReturn(Recebedor);

        tested.aceitar(idAmigo);

        verify(amigoRepository).save(amigoCaptor.capture());

        Amigo amigoCapt = amigoCaptor.getValue();

        verify(buscarSolicitacaoDeAmizadeService).porId(idAmigo);
        verify(usuarioAutenticadoService).get();
        verify(amigoRepository).save(amigo);

        assertEquals(AmizadeStatus.ACEITO, amigoCapt.getStatus());
    }
}
