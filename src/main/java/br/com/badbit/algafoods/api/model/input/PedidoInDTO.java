package br.com.badbit.algafoods.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
public class PedidoInDTO {

    @Valid
    @NotNull
    private RestauranteIdInDTO restaurante;

    @Valid
    @NotNull
    private EnderecoInDTO enderecoEntrega;

    @Valid
    @NotNull
    private FormaPagamentoIdInDTO formaPagamento;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoInDTO> itens;

}
