package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoProdutoOutDTO {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

}
