package br.com.badbit.algafoods.api.model.output;

import br.com.badbit.algafoods.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaOutDTO extends RepresentationModel<CozinhaOutDTO> {

    @JsonView(RestauranteView.Resumo.class)
    private Long id;
    @JsonView(RestauranteView.Resumo.class)
    private String nome;

}
