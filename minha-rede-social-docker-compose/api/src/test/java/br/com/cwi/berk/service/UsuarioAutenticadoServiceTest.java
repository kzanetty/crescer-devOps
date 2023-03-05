package br.com.cwi.berk.service;

import br.com.cwi.berk.security.repository.UsuarioRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioAutenticadoServiceTest {

    @InjectMocks
    private UsuarioAutenticadoService tested;

    @Mock
    private UsuarioRepository usuarioRepository;


}
