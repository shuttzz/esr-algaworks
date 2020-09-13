package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.output.GrupoOutDTO;
import br.com.badbit.algafoods.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoDTOAssembler {

    private ModelMapper modelMapper;

    public GrupoDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GrupoOutDTO toDTO(Grupo grupo) {
        return modelMapper.map(grupo, GrupoOutDTO.class);
    }

    public List<GrupoOutDTO> toCollectionDTO(Collection<Grupo> grupos) {
        return grupos.stream()
                .map(grupo -> toDTO(grupo))
                .collect(Collectors.toList());
    }

}
