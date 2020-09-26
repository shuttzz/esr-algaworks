package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.controller.CozinhaController;
import br.com.badbit.algafoods.api.model.output.CozinhaOutDTO;
import br.com.badbit.algafoods.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaDTOAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaOutDTO> {

    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public CozinhaDTOAssembler(ModelMapper modelMapper) {
        super(CozinhaController.class, CozinhaOutDTO.class);
        this.modelMapper = modelMapper;
    }

//    public CozinhaOutDTO toDTO(Cozinha cozinha) {
//        return modelMapper.map(cozinha, CozinhaOutDTO.class);
//    }
//
//    public List<CozinhaOutDTO> toCollectionDTO(List<Cozinha> cozinhas) {
//        return cozinhas.stream()
//                .map(cozinha -> toDTO(cozinha))
//                .collect(Collectors.toList());
//    }

    @Override
    public CozinhaOutDTO toModel(Cozinha cozinha) {
        CozinhaOutDTO cozinhaOutDTO = modelMapper.map(cozinha, CozinhaOutDTO.class);
        cozinhaOutDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CozinhaController.class)
                .buscar(cozinhaOutDTO.getId())).withSelfRel());
        cozinhaOutDTO.add(algaLinks.linkToCozinhas("cozinhas"));
        return cozinhaOutDTO;
    }

    @Override
    public CollectionModel<CozinhaOutDTO> toCollectionModel(Iterable<? extends Cozinha> entities) {
        return super.toCollectionModel(entities)
                .add(WebMvcLinkBuilder.linkTo(CozinhaController.class).withSelfRel());
    }
}
