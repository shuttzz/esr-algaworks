package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.input.CidadeInDTO;
import br.com.badbit.algafoods.domain.model.Cidade;
import br.com.badbit.algafoods.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDisassembler {

    private ModelMapper modelMapper;

    public CidadeInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Cidade toDomainObject(CidadeInDTO cidadeInDTO) {
        return modelMapper.map(cidadeInDTO, Cidade.class);
    }

    public void copyToDomainObject(CidadeInDTO cidadeInDTO, Cidade cidade) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // br.com.badbit.algafoods.domain.model.Estado was altered from 1 to 2
        cidade.setEstado(new Estado());

        modelMapper.map(cidadeInDTO, cidade);
    }

}
