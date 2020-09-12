package br.com.badbit.algafoods.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.badbit.algafoods.api.model.output.RestauranteOutDTO;
import br.com.badbit.algafoods.domain.model.Restaurante;

@Component
public class RestauranteDTOAssembler {
    
    private ModelMapper modelMapper;

    public RestauranteDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RestauranteOutDTO toDTO(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteOutDTO.class);
    }

    public List<RestauranteOutDTO> toCollectionDTO(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                    .map(restaurante -> toDTO(restaurante))
                    .collect(Collectors.toList());
    }

}
