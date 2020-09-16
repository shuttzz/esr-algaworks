package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

    // Com essa consulta elimino o problema do N + 1, agora o cliente, restaurante e cozinha vão ser recuperados na mesma consulta
    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
    List<Pedido> findAll();

    Optional<Pedido> findByCodigo(UUID codigo);

}
