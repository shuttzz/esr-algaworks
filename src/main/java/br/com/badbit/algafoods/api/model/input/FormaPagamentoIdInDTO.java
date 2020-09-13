package br.com.badbit.algafoods.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FormaPagamentoIdInDTO {

    @NotNull
    private Long id;

}
