package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.input.GrupoInDTO;
import br.com.badbit.algafoods.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputDisassembler {

    private ModelMapper modelMapper;

    public GrupoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Grupo toDomainObject(GrupoInDTO grupoInDTO) {
        return modelMapper.map(grupoInDTO, Grupo.class);
    }

    public void copyToDomainObject(GrupoInDTO grupoInDTO, Grupo grupo) {
        modelMapper.map(grupoInDTO, grupo);
    }

}
