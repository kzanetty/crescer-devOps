package br.com.cwi.berk.controller.request;

import br.com.cwi.berk.domain.enums.VisibilidadePublicacao;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IncluirPostagemRequest {

    @NotNull
    private Long idUsuario;
    @NotBlank
    private String descricao;
    @NotBlank
    private String imageUrl;
    @NotNull
    private VisibilidadePublicacao visibilidade;
}