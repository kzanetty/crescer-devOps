package br.com.cwi.berk.factory;

import br.com.cwi.berk.controller.request.IncluirPostagemRequest;
import br.com.cwi.berk.domain.Postagem;
import br.com.cwi.berk.domain.enums.VisibilidadePublicacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PostagemFactory {

    public static Postagem getEntity() {
        Postagem postagem = new Postagem();
        postagem.setId(SimpleFactory.getRandomLong());
        postagem.setAutor(UsuarioFactory.get());
        postagem.setDescricao("Eis aqui uma grande descrição.");
        postagem.setImageUrl("Url da imagem da postagem");
        postagem.setDataCriacao(LocalDateTime.of(LocalDate.of(1999, 12, 1), LocalTime.of(6, 30, 45)));
        postagem.setVisibilidade(VisibilidadePublicacao.PUBLICO);
        return postagem;
    }

    public static IncluirPostagemRequest getRequest() {
        IncluirPostagemRequest postagem = new IncluirPostagemRequest();
        postagem.setIdUsuario(SimpleFactory.getRandomLong());
        postagem.setDescricao("Eis aqui uma grande descrição.");
        postagem.setImageUrl("Url da imagem da postagem");
        postagem.setVisibilidade(VisibilidadePublicacao.PUBLICO);
        return postagem;
    }

}


