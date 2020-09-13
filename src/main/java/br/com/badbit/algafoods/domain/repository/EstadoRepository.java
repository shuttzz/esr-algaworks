package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.Estado;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EstadoRepository extends CustomJpaRepository<Estado, Long> {

    Optional<Estado> findByCodigo(UUID codigo);

}
