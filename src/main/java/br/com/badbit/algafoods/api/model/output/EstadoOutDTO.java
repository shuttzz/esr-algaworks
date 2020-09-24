package br.com.badbit.algafoods.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EstadoOutDTO {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Goiás")
    private String nome;

}
