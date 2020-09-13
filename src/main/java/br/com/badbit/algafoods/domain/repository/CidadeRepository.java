package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.Cidade;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long> {

    Optional<Cidade> findByCodigo(UUID codigo);

}
