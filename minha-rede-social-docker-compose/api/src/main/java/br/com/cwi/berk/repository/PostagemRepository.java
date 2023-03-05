package br.com.cwi.berk.repository;

import br.com.cwi.berk.domain.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    Page<Postagem> findAllByAutorId(Long id, Pageable pageable);
}
