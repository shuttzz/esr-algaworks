package br.com.badbit.algafoods.infrastructure.repository.spec;

import br.com.badbit.algafoods.domain.model.Pedido;
import br.com.badbit.algafoods.domain.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            // Esse if é para não ter problemas com o count, pois só faz sentido existir esse fetch quando for consulta
            // utilizando o filtro para retornar um Pedido, ou seja, quando o JPA vai fazer o count para saber quantos
            // elementos tem ele não pode tentar fazer um fetch, então o por isso foi criado esse if
            if (Pedido.class.equals(criteriaQuery.getResultType())) {
                // Resolvendo o problema do N + 1
                root.fetch("restaurante").fetch("cozinha");
                root.fetch("cliente");
            }

            var predicates = new ArrayList<Predicate>();

            if (filtro.getClienteId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("cliente"), filtro.getClienteId()));
            }

            if (filtro.getRestauranteId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }

            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }

            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
