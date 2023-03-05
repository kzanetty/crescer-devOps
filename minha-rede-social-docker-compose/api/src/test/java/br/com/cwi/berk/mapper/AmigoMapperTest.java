package br.com.cwi.berk.mapper;

import br.com.cwi.berk.controller.response.AmigoResponse;
import br.com.cwi.berk.domain.Amigo;
import br.com.cwi.berk.factory.AmigoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@ExtendWith(MockitoExtension.class)
public class AmigoMapperTest {

    @Test
    @DisplayName("Deve retornar corretamente o response de amigo")
    void deveRetornarResponseCompleto() {

        Amigo amigo = AmigoFactory.get();

        AmigoResponse response = AmigoMapper.toResponse(amigo);

        assertEquals(amigo.getId(), response.getId());
        assertEquals(amigo.getRecebedor(), response.getRecebedor());
        assertEquals(amigo.getSolicitante(), response.getSolicitante());
        assertEquals(amigo.getStatus(), response.getStatus());
    }

    @Test
    @DisplayName("Deve retornar response vazio quando request for nulo")
    void deveRetornarResponseVazio() {

        AmigoResponse response = AmigoMapper.toResponse(null);

        assertNotNull(response);
    }



}
