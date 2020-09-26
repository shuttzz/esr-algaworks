package br.com.badbit.algafoods.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeOutDTO extends RepresentationModel<CidadeOutDTO> {

//    @ApiModelProperty(value = "ID da cidade", example = "1")
    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Goiânia")
    private String nome;
    private EstadoOutDTO estado;

}
