package br.com.badbit.algafoods.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cidades")
public class Cidade {

    @Id
    @SequenceGenerator(name = "cidades_id_seq", sequenceName = "cidades_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidades_id_seq")
    @EqualsAndHashCode.Include
    private Long id;

    private UUID codigo;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

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
