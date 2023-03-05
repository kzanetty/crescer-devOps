package br.com.cwi.berk.repository;

import br.com.cwi.berk.domain.Amigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmigoRepository extends JpaRepository<Amigo, Long> {
}
