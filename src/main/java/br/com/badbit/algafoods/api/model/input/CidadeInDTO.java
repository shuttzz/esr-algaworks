package br.com.badbit.algafoods.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInDTO {

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoCidadeInDTO estado;

}
