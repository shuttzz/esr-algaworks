package br.com.badbit.algafoods.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "permissoes")
public class Permissao {

    @Id
    @SequenceGenerator(name = "permissoes_id_seq", sequenceName = "permissoes_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissoes_id_seq")
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    private String descricao;

}
