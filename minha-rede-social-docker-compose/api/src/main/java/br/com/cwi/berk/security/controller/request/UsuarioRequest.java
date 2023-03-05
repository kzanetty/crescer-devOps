package br.com.cwi.berk.security.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank
    private String nome;

    @Email
    @NotNull
    private String email;

    private String apelido;
    @NotNull
    private LocalDate dataNascimento;
    private String imageUrl;

    @NotBlank
    private String senha;
}
