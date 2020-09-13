package br.com.badbit.algafoods.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "itens_pedidos")
public class ItemPedido {

    @Id
    @SequenceGenerator(name = "itens_pedidos_id_seq", sequenceName = "itens_pedidos_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itens_pedidos_id_seq")
    @EqualsAndHashCode.Include
    private Long id;
    private UUID codigo;
    private Integer quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name = "preco_total")
    private BigDecimal precoTotal;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    private String observacao;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public void calcularPrecoTotal() {
        BigDecimal precoUnitario = this.getPrecoUnitario();
        Integer quantidade = this.getQuantidade();
        if (precoUnitario == null) {
            precoUnitario = BigDecimal.ZERO;
        }

        if (quantidade == null) {
            quantidade = 0;
        }

        this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
    }

    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID());
    }

}
