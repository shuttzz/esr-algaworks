package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsuarioOutDTO {

    private UUID codigo;
    private String nome;
    private String email;

}
