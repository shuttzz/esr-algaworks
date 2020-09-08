package br.com.badbit.algafoods.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @SequenceGenerator(name = "pedidos_id_seq", sequenceName = "pedidos_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidos_id_seq")
    @EqualsAndHashCode.Include
    private Long id;
    private BigDecimal subtotal;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido statusPedido;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedido;

    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;

    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;

    @Column(name = "data_entrega")
    private LocalDateTime dataEntrega;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
