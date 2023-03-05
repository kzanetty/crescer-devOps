package br.com.cwi.berk.domain;

import br.com.cwi.berk.domain.enums.VisibilidadePublicacao;
import br.com.cwi.berk.security.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario autor;

    private String descricao;
    private String imageUrl;
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private VisibilidadePublicacao visibilidade;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "curtida",
            joinColumns = @JoinColumn(name = "id_postagem"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private List<Usuario> curtidas;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "comentario",
            joinColumns = @JoinColumn(name = "id_postagem"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private List<Comentario> comentarios;

}

