package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeOutDTO {

    private Long id;
    private String nome;
    private EstadoOutDTO estado;

}
