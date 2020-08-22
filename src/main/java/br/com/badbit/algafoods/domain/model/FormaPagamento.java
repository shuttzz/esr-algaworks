package br.com.badbit.algafoods.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "formas_pagamento")
public class FormaPagamento {

    @Id
    @SequenceGenerator(name = "formas_pagamento_id_seq", sequenceName = "formas_pagamento_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formas_pagamento_id_seq")
    @EqualsAndHashCode.Include
    private Long id;
    private String descricao;

}
