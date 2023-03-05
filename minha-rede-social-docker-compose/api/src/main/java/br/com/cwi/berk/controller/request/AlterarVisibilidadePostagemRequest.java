package br.com.cwi.berk.controller.request;

import br.com.cwi.berk.domain.enums.VisibilidadePublicacao;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlterarVisibilidadePostagemRequest {

    @NotNull
    private VisibilidadePublicacao visibilidade;
}
