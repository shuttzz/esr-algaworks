package br.com.badbit.algafoods.api.model.output;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteOutDTO {

    private UUID codigo;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaOutDTO cozinha;
    private Boolean ativo;
    private Boolean aberto;
    private EnderecoOutDTO endereco;

}
