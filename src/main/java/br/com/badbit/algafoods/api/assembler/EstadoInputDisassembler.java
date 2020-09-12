package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.input.EstadoInDTO;
import br.com.badbit.algafoods.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputDisassembler {

    private ModelMapper modelMapper;

    public EstadoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Estado toDomainObject(EstadoInDTO estadoInDTO) {
        return modelMapper.map(estadoInDTO, Estado.class);
    }

    public void copyToDomainObject(EstadoInDTO estadoInDTO, Estado estado) {
        modelMapper.map(estadoInDTO, estado);
    }

}
