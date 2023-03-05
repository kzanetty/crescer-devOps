package br.com.cwi.berk.domain;

import br.com.cwi.berk.domain.enums.AmizadeStatus;
import br.com.cwi.berk.security.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Amigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario_solicitante")
    private Usuario solicitante;

    @OneToOne
    @JoinColumn(name = "id_usuario_recebedor")
    private Usuario recebedor;

    @Enumerated(EnumType.STRING)
    private AmizadeStatus status;
}
