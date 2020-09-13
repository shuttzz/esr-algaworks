package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

    Optional<Usuario> findByCodigo(UUID codigo);

    Optional<Usuario> findByEmail(String email);

}
