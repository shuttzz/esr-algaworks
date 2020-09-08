package br.com.badbit.algafoods.api.model.mixin;

import br.com.badbit.algafoods.domain.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public abstract class PedidoMixin {

    @JsonIgnore
    private LocalDateTime updatedAt;

}
