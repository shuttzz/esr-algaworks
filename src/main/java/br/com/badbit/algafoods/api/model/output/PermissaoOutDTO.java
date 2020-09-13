package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PermissaoOutDTO {

    private Long id;
    private String nome;
    private String descricao;

}