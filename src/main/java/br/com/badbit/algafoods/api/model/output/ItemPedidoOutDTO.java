package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ItemPedidoOutDTO extends RepresentationModel<ItemPedidoOutDTO> {

    private Long id;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;

}
