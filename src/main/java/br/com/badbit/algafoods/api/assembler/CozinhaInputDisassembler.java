package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.input.CozinhaInDTO;
import br.com.badbit.algafoods.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDisassembler {

    private ModelMapper modelMapper;

    public CozinhaInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Cozinha toDomainObject(CozinhaInDTO cozinhaInDTO) {
        return modelMapper.map(cozinhaInDTO, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaInDTO cozinhaInDTO, Cozinha cozinha) {
        modelMapper.map(cozinhaInDTO, cozinha);
    }

}
