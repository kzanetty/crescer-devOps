package br.com.cwi.berk.security.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private String apelido;
    private LocalDate dataNascimento;
    private String imageUrl;
}




