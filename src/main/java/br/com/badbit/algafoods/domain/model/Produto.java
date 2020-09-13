package br.com.badbit.algafoods.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @SequenceGenerator(name = "produtos_id_seq", sequenceName = "produtos_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produtos_id_seq")
    @EqualsAndHashCode.Include
    private Long id;
    private UUID codigo;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID());
    }

}
