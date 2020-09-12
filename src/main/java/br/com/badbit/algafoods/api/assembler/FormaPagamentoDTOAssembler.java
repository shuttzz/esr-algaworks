package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.output.FormaPagamentoOutDTO;
import br.com.badbit.algafoods.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoDTOAssembler {

    private ModelMapper modelMapper;

    public FormaPagamentoDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FormaPagamentoOutDTO toDTO(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoOutDTO.class);
    }

    public List<FormaPagamentoOutDTO> toCollectionDTO(Collection<FormaPagamento> formasPagamento) {
        return formasPagamento.stream()
                .map(estado -> toDTO(estado))
                .collect(Collectors.toList());
    }

}
