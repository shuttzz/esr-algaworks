package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.Grupo;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GrupoRepository extends CustomJpaRepository<Grupo, Long> {

    Optional<Grupo> findByCodigo(UUID codigo);

}
