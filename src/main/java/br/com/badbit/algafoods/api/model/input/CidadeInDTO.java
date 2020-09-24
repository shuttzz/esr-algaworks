package br.com.badbit.algafoods.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInDTO {

    @ApiModelProperty(example = "Goiânia", required = true)
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInDTO estado;

}
