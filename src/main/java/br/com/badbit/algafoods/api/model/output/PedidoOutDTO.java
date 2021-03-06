package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoOutDTO extends RepresentationModel<PedidoOutDTO> {

    private UUID codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataEntrega;
    private OffsetDateTime dataCancelamento;
    private RestauranteApenasNomeModel restaurante;
    private UsuarioOutDTO cliente;
    private FormaPagamentoOutDTO formaPagamento;
    private EnderecoOutDTO enderecoEntrega;
    private List<ItemPedidoOutDTO> itens;

}
