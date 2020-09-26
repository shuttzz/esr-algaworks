package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
public class FormaPagamentoOutDTO extends RepresentationModel<FormaPagamentoOutDTO> {

    private Long id;
    private String descricao;

}
