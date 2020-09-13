package br.com.badbit.algafoods.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class ItemPedidoInDTO {

    @NotNull
    private Long produtoId;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    private String observacao;

}
