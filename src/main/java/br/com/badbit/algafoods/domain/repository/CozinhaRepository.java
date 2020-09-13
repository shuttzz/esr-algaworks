package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.Cozinha;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

    Optional<Cozinha> findByCodigo(UUID codigo);
    List<Cozinha> findByNomeContaining(String nome);
    Optional<Cozinha> findByNome(String nome);

}
