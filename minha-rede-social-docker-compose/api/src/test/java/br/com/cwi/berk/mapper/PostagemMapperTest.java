package br.com.cwi.berk.mapper;

import br.com.cwi.berk.controller.response.PostagemResponse;
import br.com.cwi.berk.domain.Postagem;
import br.com.cwi.berk.factory.PostagemFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PostagemMapperTest {

    @Test
    @DisplayName("Deve retornar corretamente o toResponse de postagem")
    void deveRetornarParaResponse() {

        Postagem postagem = PostagemFactory.getEntity();

        PostagemResponse response = PostagemMapper.toResponse(postagem);

        assertEquals(postagem.getId(), response.getId());
        assertEquals(postagem.getAutor(), response.getAutor());
        assertEquals(postagem.getDescricao(), response.getDescricao());
        assertEquals(postagem.getDataCriacao(), response.getDataCriacao());
        assertEquals(postagem.getImageUrl(), response.getImageUrl());
    }

    @Test
    @DisplayName("Deve retornar response vazio quando request for nulo")
    void deveRetornarResponseVazioParaRequestNull() {
        PostagemResponse response = PostagemMapper.toResponse(null);
        assertNotNull(response);
    }

    @Test
    @DisplayName("Deve retornar response vazio quando entity for nulo")
    void deveRetornarResponseVazioParaEntityNull() {
        Postagem response = PostagemMapper.toEntity(null);
        assertNotNull(response);
    }
}
