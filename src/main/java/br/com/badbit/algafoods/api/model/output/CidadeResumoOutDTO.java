package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CidadeResumoOutDTO {

    private Long id;
    private String nome;
    private String estado;

}
