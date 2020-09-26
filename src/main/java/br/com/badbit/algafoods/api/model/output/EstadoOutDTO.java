package br.com.badbit.algafoods.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(collectionRelation = "estados")
@Getter
@Setter
public class EstadoOutDTO extends RepresentationModel<EstadoOutDTO> {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Goiás")
    private String nome;

}
