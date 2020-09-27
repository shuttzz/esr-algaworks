package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(collectionRelation = "grupos")
@Getter
@Setter
public class GrupoOutDTO extends RepresentationModel<GrupoOutDTO> {

    private Long id;
    private String nome;

}
