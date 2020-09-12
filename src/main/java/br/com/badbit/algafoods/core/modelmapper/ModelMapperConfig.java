package br.com.badbit.algafoods.core.modelmapper;

import br.com.badbit.algafoods.api.model.output.EnderecoOutDTO;
import br.com.badbit.algafoods.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        var enderecoToEnderecoOutDTOTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoOutDTO.class);
        enderecoToEnderecoOutDTOTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoOutDTODest, value) -> enderecoOutDTODest.getCidade().setEstado(value));

        return modelMapper;
    }

}
