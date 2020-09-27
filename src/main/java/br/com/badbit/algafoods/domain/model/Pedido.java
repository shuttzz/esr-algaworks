package br.com.badbit.algafoods.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import br.com.badbit.algafoods.domain.event.PedidoCanceladoEvent;
import br.com.badbit.algafoods.domain.event.PedidoConfirmadoEvent;
import br.com.badbit.algafoods.domain.exception.NegocioException;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.AbstractAggregateRoot;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "pedidos")
public class Pedido extends AbstractAggregateRoot<Pedido> {

    @Id
    @SequenceGenerator(name = "pedidos_id_seq", sequenceName = "pedidos_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidos_id_seq")
    @EqualsAndHashCode.Include
    private Long id;
    private UUID codigo;
    private BigDecimal subtotal;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Embedded
    private Endereco enderecoEntrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido statusPedido = StatusPedido.CRIADO;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

    @Column(name = "data_confirmacao")
    private OffsetDateTime dataConfirmacao;

    @Column(name = "data_cancelamento")
    private OffsetDateTime dataCancelamento;

    @Column(name = "data_entrega")
    private OffsetDateTime dataEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    // Com esse CascadeType.ALL eu vou salvar os itensPedido quando mandar salvar o Pedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido = new ArrayList<>();

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public void calcularValorTotal() {
        getItensPedido().forEach(ItemPedido::calcularPrecoTotal);

        this.subtotal = getItensPedido().stream()
                .map(item -> item.getPrecoTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subtotal.add(this.taxaFrete);
    }

    public void confirmar() {
        setStatusPedido(StatusPedido.CONFIRMADO);
        setDataConfirmacao(OffsetDateTime.now());
        registerEvent(new PedidoConfirmadoEvent(this));
    }

    public void entregar() {
        setStatusPedido(StatusPedido.ENTREGUE);
        setDataEntrega(OffsetDateTime.now());
    }

    public void cancelar() {
        setStatusPedido(StatusPedido.CANCELADO);
        setDataCancelamento(OffsetDateTime.now());
        registerEvent(new PedidoCanceladoEvent(this));
    }

    public boolean podeSerConfirmado() {
        return getStatusPedido().podeAlterarPara(StatusPedido.CONFIRMADO);
    }

    public boolean podeSerEntregue() {
        return getStatusPedido().podeAlterarPara(StatusPedido.ENTREGUE);
    }

    public boolean podeSerCancelado() {
        return getStatusPedido().podeAlterarPara(StatusPedido.CANCELADO);
    }

    private void setStatus(StatusPedido novoStatus) {
        if (getStatusPedido().naoPodeAlterarPara(novoStatus)) {
            throw new NegocioException(
                    String.format("Status do pedido %s não pode ser alterado de %s para %s",
                            getCodigo(), getStatusPedido().getDescricao(), novoStatus.getDescricao() )
            );
        }

        this.statusPedido = novoStatus;
    }

    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID());
    }

}
