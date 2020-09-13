package br.com.badbit.algafoods.core.modelmapper;

import br.com.badbit.algafoods.api.model.input.ItemPedidoInDTO;
import br.com.badbit.algafoods.api.model.output.EnderecoOutDTO;
import br.com.badbit.algafoods.domain.model.Endereco;
import br.com.badbit.algafoods.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(ItemPedidoInDTO.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var enderecoToEnderecoOutDTOTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoOutDTO.class);
        enderecoToEnderecoOutDTOTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoOutDTODest, value) -> enderecoOutDTODest.getCidade().setEstado(value));

        return modelMapper;
    }

}
