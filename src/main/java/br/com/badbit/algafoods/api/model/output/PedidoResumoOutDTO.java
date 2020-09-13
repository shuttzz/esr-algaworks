package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PedidoResumoOutDTO {

    private UUID codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private RestaurantePedidoOutDTO restaurante;
    private UsuarioOutDTO cliente;

}
