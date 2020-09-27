package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.controller.FormaPagamentoController;
import br.com.badbit.algafoods.api.model.output.FormaPagamentoOutDTO;
import br.com.badbit.algafoods.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoDTOAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoOutDTO> {

    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public FormaPagamentoDTOAssembler(ModelMapper modelMapper) {
        super(FormaPagamentoController.class, FormaPagamentoOutDTO.class);
        this.modelMapper = modelMapper;
    }

    @Override
    public FormaPagamentoOutDTO toModel(FormaPagamento formaPagamento) {
        FormaPagamentoOutDTO formaPagamentoModel =
                createModelWithId(formaPagamento.getId(), formaPagamento);

        modelMapper.map(formaPagamento, formaPagamentoModel);

        formaPagamentoModel.add(algaLinks.linkToFormasPagamento("formasPagamento"));

        return formaPagamentoModel;
    }

    @Override
    public CollectionModel<FormaPagamentoOutDTO> toCollectionModel(Iterable<? extends FormaPagamento> formasPagamento) {
        return super.toCollectionModel(formasPagamento)
                .add(algaLinks.linkToFormasPagamento());
    }

}
