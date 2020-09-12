package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.output.CidadeOutDTO;
import br.com.badbit.algafoods.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeDTOAssembler {

    private ModelMapper modelMapper;

    public CidadeDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CidadeOutDTO toDTO(Cidade cidade) {
        return modelMapper.map(cidade, CidadeOutDTO.class);
    }

    public List<CidadeOutDTO> toCollectionDTO(List<Cidade> cidades) {
        return cidades.stream()
                .map(cidade -> toDTO(cidade))
                .collect(Collectors.toList());
    }

}
