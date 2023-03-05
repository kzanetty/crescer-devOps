package br.com.cwi.berk.factory;

import br.com.cwi.berk.security.domain.Usuario;

import java.time.LocalDate;

public class UsuarioFactory {

    public static Usuario get() {
        Usuario usuario = new Usuario();
        usuario.setId(SimpleFactory.getRandomLong());
        usuario.setNome("Usuário de teste");
        usuario.setEmail("teste@cwi.com.br");
        usuario.setSenha("123456");
        usuario.setAtivo(true);
        usuario.setApelido("knabach");
        usuario.setDataNascimento(LocalDate.of(1999, 12, 1));
        usuario.setImageUrl("Imagina que tem uma URL de imagem aqui.");
        return usuario;
    }
}