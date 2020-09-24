package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormaPagamentoRepository extends CustomJpaRepository<FormaPagamento, Long> {

    Optional<FormaPagamento> findByCodigo(UUID codigo);

    @Query("select max(updatedAt) from FormaPagamento")
    OffsetDateTime getDataUltimaAtualizacao();

}
