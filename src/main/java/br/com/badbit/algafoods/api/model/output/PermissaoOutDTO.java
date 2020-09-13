package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class PermissaoOutDTO {

    private UUID codigo;
    private String nome;
    private String descricao;

}
