package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.controller.EstadoController;
import br.com.badbit.algafoods.api.model.output.EstadoOutDTO;
import br.com.badbit.algafoods.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoDTOAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoOutDTO> {

    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public EstadoDTOAssembler(ModelMapper modelMapper) {
        super(EstadoController.class, EstadoOutDTO.class);
        this.modelMapper = modelMapper;
    }

//    public EstadoOutDTO toDTO(Estado estado) {
//        return modelMapper.map(estado, EstadoOutDTO.class);
//    }

//    public List<EstadoOutDTO> toCollectionDTO(List<Estado> estados) {
//        return estados.stream()
//                .map(estado -> toDTO(estado))
//                .collect(Collectors.toList());
//    }

    @Override
    public EstadoOutDTO toModel(Estado estado) {
        EstadoOutDTO estadoOutDTO = modelMapper.map(estado, EstadoOutDTO.class);

        estadoOutDTO.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(EstadoController.class)
                        .buscar(estadoOutDTO.getId())).withSelfRel());
        estadoOutDTO.add(algaLinks.linkToEstados("estados"));

        return estadoOutDTO;
    }

    @Override
    public CollectionModel<EstadoOutDTO> toCollectionModel(Iterable<? extends Estado> estados) {
        return super.toCollectionModel(estados)
                .add(algaLinks.linkToEstados());
    }
}
