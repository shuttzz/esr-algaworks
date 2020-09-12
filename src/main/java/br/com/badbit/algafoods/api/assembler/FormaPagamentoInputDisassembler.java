package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.input.FormaPagamentoInDTO;
import br.com.badbit.algafoods.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoInputDisassembler {

    private ModelMapper modelMapper;

    public FormaPagamentoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FormaPagamento toDomainObject(FormaPagamentoInDTO formaPagamentoInDTO) {
        return modelMapper.map(formaPagamentoInDTO, FormaPagamento.class);
    }

    public void copyToDomainObject(FormaPagamentoInDTO formaPagamentoInDTO, FormaPagamento formaPagamento) {
        modelMapper.map(formaPagamentoInDTO, formaPagamento);
    }

}
