package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeResumoOutDTO extends RepresentationModel<CidadeResumoOutDTO> {

    private Long id;
    private String nome;
    private String estado;

}
