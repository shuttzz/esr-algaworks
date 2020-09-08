package br.com.badbit.algafoods.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public abstract class ProdutoMixin {

    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime updatedAt;

}
