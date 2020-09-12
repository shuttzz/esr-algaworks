package br.com.badbit.algafoods.api.model.output;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteOutDTO {
    
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaOutDTO cozinha;
    private Boolean ativo;
    private EnderecoOutDTO endereco;

}
