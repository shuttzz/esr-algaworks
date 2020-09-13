package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.FormaPagamento;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormaPagamentoRepository extends CustomJpaRepository<FormaPagamento, Long> {

    Optional<FormaPagamento> findByCodigo(UUID codigo);

}
