package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoOutDTO {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResumoOutDTO cidade;

}
