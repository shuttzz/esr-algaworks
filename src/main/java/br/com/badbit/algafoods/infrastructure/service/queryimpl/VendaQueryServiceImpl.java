package br.com.badbit.algafoods.infrastructure.service.queryimpl;

import br.com.badbit.algafoods.domain.model.Pedido;
import br.com.badbit.algafoods.domain.model.StatusPedido;
import br.com.badbit.algafoods.domain.model.dto.VendaDiaria;
import br.com.badbit.algafoods.domain.filter.VendaDiariaFilter;
import br.com.badbit.algafoods.domain.service.VendaQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);
        var predicates = new ArrayList<Predicate>();

        // Específico para o MySql
        var functionConvertTzDataCriacao = builder.function(
                "convert_tz", LocalDate.class, root.get("dataCriacao"),
                builder.literal("+00:00"), builder.literal(timeOffset)
        );
//        var functionDateDataCriacao = builder.function("date", LocalDate.class, functionConvertTzDataCriacao);

        // Específico para o postgresql, porém está incompleto
//        var functionDateDataCriacao = builder.function("date_trunc", LocalDateTime.class, builder.literal("hour"), root.get("dataCriacao"));
        var functionDateDataCriacao = builder.function("date_trunc", LocalDateTime.class, builder.literal("hour"), root.get("dataCriacao"));

        var selection = builder.construct(VendaDiaria.class,
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal"))
        );

        if (filtro.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
        }

        if (filtro.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
                    filtro.getDataCriacaoInicio()));
        }

        if (filtro.getDataCriacaoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                    filtro.getDataCriacaoFim()));
        }

        predicates.add(root.get("statusPedido").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionDateDataCriacao);

        return manager.createQuery(query).getResultList();
    }

}
