package br.com.badbit.algafoods.api.model.mixin;

import br.com.badbit.algafoods.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

public abstract class CidadeMixin {


    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;

    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime updatedAt;

}
