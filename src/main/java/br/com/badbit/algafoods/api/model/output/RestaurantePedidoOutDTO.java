package br.com.badbit.algafoods.api.model.output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Getter
@Setter
public class RestaurantePedidoOutDTO extends RepresentationModel<RestaurantePedidoOutDTO> {

    private Long id;
    private String nome;

}
