package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
        RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

//    @Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
    @Query("from Restaurante r join fetch r.cozinha")
    List<Restaurante> findAll();
    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
    List<Restaurante> findByNomeContaining(String nome);

}
