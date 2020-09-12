package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.domain.model.Cidade;
import br.com.badbit.algafoods.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.badbit.algafoods.api.model.input.RestauranteInDTO;
import br.com.badbit.algafoods.domain.model.Cozinha;
import br.com.badbit.algafoods.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {
    
    private ModelMapper modelMapper;

    public RestauranteInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Restaurante toDomainObject(RestauranteInDTO restauranteInDTO) {
        return modelMapper.map(restauranteInDTO, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInDTO restauranteInDTO, Restaurante restaurante) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of 
        // br.com.badbit.algafoods.domain.model.Cozinha was altered from 1 to 2
        restaurante.setCozinha(new Cozinha());
        if (restaurante.getEndereco() != null) {
            restaurante.getEndereco().setCidade(new Cidade());
        }
        
        modelMapper.map(restauranteInDTO, restaurante);
    }

}
