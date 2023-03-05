package br.com.cwi.berk.security.repository;

import br.com.cwi.berk.security.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Page<Usuario> findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(String text, String text1, Pageable pageable);
}
