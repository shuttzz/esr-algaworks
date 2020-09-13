package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.Permissao;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {

    Optional<Permissao> findByCodigo(UUID codigo);

}
