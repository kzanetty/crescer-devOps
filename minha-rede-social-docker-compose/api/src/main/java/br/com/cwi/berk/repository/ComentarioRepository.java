package br.com.cwi.berk.repository;

import br.com.cwi.berk.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
