package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.output.EstadoOutDTO;
import br.com.badbit.algafoods.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoDTOAssembler {

    private ModelMapper modelMapper;

    public EstadoDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EstadoOutDTO toDTO(Estado estado) {
        return modelMapper.map(estado, EstadoOutDTO.class);
    }

    public List<EstadoOutDTO> toCollectionDTO(List<Estado> estados) {
        return estados.stream()
                .map(estado -> toDTO(estado))
                .collect(Collectors.toList());
    }

}
