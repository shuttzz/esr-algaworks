package br.com.badbit.algafoods.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdInDTO {
    
    @NotNull
    private Long id;

}
