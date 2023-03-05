package br.com.cwi.berk.domain;

import br.com.cwi.berk.security.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_postagem")
    private Postagem Postagem;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario Usuario;

    private String comentario;
}
