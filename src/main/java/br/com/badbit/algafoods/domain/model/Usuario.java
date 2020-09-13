package br.com.badbit.algafoods.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "usuarios")
public class Usuario {

    @Id
    @SequenceGenerator(name = "usuarios_id_seq", sequenceName = "usuarios_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_id_seq")
    @EqualsAndHashCode.Include
    private Long id;
    private UUID codigo;
    private String nome;
    private String email;
    private String senha;

    @ManyToMany
    @JoinTable(name = "usuarios_grupos", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private Set<Grupo> grupos = new HashSet<>();

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public boolean senhaCoincideCom(String senha) {
        return getSenha().equals(senha);
    }

    public boolean senhaNaoCoincideCom(String senha) {
        return !senhaCoincideCom(senha);
    }

    public boolean removerGrupo(Grupo grupo) {
        return getGrupos().remove(grupo);
    }

    public boolean adicionarGrupo(Grupo grupo) {
        return getGrupos().add(grupo);
    }

    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID());
    }

}
