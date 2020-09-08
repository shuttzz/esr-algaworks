package br.com.badbit.algafoods.api.model.mixin;

import br.com.badbit.algafoods.domain.model.Pedido;
import br.com.badbit.algafoods.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class ItemPedidoMixin {

    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime updatedAt;

}
