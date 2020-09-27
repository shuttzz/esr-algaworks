package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

//@JsonFilter("pedidoFilter")
@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResumoOutDTO extends RepresentationModel<PedidoResumoOutDTO> {

    private UUID codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private RestauranteApenasNomeModel restaurante;
    private UsuarioOutDTO cliente;

}
