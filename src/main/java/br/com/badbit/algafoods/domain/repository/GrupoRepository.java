package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.Grupo;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends CustomJpaRepository<Grupo, Long> {
}
