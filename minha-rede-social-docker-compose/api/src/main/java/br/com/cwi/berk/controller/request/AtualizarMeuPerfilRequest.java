package br.com.cwi.berk.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarMeuPerfilRequest {

    private String nome;
    private String apelido;
    private String imageUrl;
}
