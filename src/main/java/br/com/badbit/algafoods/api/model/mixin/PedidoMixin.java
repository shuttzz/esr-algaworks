package br.com.badbit.algafoods.api.model.mixin;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class PedidoMixin {

    @JsonIgnore
    private LocalDateTime updatedAt;

}
