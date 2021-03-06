package br.com.badbit.algafoods.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @SequenceGenerator(name = "grupos_id_seq", sequenceName = "grupos_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grupos_id_seq")
    @EqualsAndHashCode.Include
    private Long id;
    private UUID codigo;
    private String nome;

    @ManyToMany
    @JoinTable(name = "grupos_permissoes", joinColumns = @JoinColumn(name = "grupo_id"))
    private Set<Permissao> permissoes = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public boolean removerPermissao(Permissao permissao) {
        return getPermissoes().remove(permissao);
    }

    public boolean adicionarPermissao(Permissao permissao) {
        return getPermissoes().add(permissao);
    }

    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID());
    }

}
