package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioOutDTO {

    private Long id;
    private String nome;
    private String email;

}
