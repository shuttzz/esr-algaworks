package br.com.badbit.algafoods.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioInDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

}
