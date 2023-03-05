package br.com.cwi.berk.controller.response;

import br.com.cwi.berk.domain.Comentario;
import br.com.cwi.berk.domain.enums.VisibilidadePublicacao;
import br.com.cwi.berk.security.domain.Usuario;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostagemResponse {

    private Long id;
    private Usuario autor;
    private String descricao;
    private String imageUrl;
    private LocalDateTime dataCriacao;
    private VisibilidadePublicacao visibilidade;
    private List<Usuario> curtidas;
    private List<Comentario> comentarios;
}
