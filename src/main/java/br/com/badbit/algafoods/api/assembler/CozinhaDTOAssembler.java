package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.output.CozinhaOutDTO;
import br.com.badbit.algafoods.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaDTOAssembler {

    private ModelMapper modelMapper;

    public CozinhaDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CozinhaOutDTO toDTO(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaOutDTO.class);
    }

    public List<CozinhaOutDTO> toCollectionDTO(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toDTO(cozinha))
                .collect(Collectors.toList());
    }

}
