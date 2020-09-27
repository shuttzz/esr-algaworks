package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.util.UUID;

@Relation(collectionRelation = "permissoes")
@Getter
@Setter
public class PermissaoOutDTO extends RepresentationModel<PermissaoOutDTO> {

    private Long id;
    private String nome;
    private String descricao;

}
