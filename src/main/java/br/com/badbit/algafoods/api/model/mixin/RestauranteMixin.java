package br.com.badbit.algafoods.api.model.mixin;

import br.com.badbit.algafoods.domain.model.Cozinha;
import br.com.badbit.algafoods.domain.model.Endereco;
import br.com.badbit.algafoods.domain.model.FormaPagamento;
import br.com.badbit.algafoods.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class RestauranteMixin {

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "nome"}, allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime updatedAt;

}
