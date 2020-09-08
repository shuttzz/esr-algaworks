package br.com.badbit.algafoods.api.model.mixin;

import br.com.badbit.algafoods.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes = new ArrayList<>();

    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime updatedAt;

}
