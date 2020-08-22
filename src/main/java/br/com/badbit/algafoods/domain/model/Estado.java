package br.com.badbit.algafoods.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "estados")
public class Estado {

    @Id
    @SequenceGenerator(name = "estados_id_seq", sequenceName = "estados_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estados_id_seq")
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;

}
