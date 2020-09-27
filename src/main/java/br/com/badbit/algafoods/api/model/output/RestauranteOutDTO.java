package br.com.badbit.algafoods.api.model.output;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.badbit.algafoods.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteOutDTO extends RepresentationModel<RestauranteOutDTO> {

//    @JsonView(RestauranteView.Resumo.class)
    private Long id;
//    @JsonView(RestauranteView.Resumo.class)
    private String nome;
//    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;
//    @JsonView(RestauranteView.Resumo.class)
    private CozinhaOutDTO cozinha;
    private Boolean ativo;
    private Boolean aberto;
    private EnderecoOutDTO endereco;

}
