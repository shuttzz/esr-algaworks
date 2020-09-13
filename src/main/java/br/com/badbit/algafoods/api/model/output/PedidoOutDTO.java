package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PedidoOutDTO {

    private UUID codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataEntrega;
    private OffsetDateTime dataCancelamento;
    private RestaurantePedidoOutDTO restaurante;
    private UsuarioOutDTO cliente;
    private FormaPagamentoOutDTO formaPagamento;
    private EnderecoOutDTO enderecoEntrega;
    private List<ItemPedidoOutDTO> itens;

}
