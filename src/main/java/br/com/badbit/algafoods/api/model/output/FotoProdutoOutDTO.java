package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "fotos")
@Setter
@Getter
public class FotoProdutoOutDTO extends RepresentationModel<FotoProdutoOutDTO> {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

}
