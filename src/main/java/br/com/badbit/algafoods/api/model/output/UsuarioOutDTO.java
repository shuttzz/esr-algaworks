package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioOutDTO extends RepresentationModel<UsuarioOutDTO> {

    private Long id;
    private String nome;
    private String email;

}
