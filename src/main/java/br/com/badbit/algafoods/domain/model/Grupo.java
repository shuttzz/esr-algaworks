package br.com.badbit.algafoods.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String nome;

    @ManyToMany
    @JoinTable(name = "grupos_permissoes", joinColumns = @JoinColumn(name = "grupo_id"))
    private List<Permissao> permissoes = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
