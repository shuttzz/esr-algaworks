package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.controller.CidadeController;
import br.com.badbit.algafoods.api.controller.EstadoController;
import br.com.badbit.algafoods.api.model.output.CidadeOutDTO;
import br.com.badbit.algafoods.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeDTOAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeOutDTO> {

    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public CidadeDTOAssembler(ModelMapper modelMapper) {
        super(CidadeController.class, CidadeOutDTO.class);
        this.modelMapper = modelMapper;
    }

//    public CidadeOutDTO toDTO(Cidade cidade) {
//        return modelMapper.map(cidade, CidadeOutDTO.class);
//    }
//
//    public List<CidadeOutDTO> toCollectionDTO(List<Cidade> cidades) {
//        return cidades.stream()
//                .map(cidade -> toDTO(cidade))
//                .collect(Collectors.toList());
//    }

    @Override
    public CidadeOutDTO toModel(Cidade cidade) {
        CidadeOutDTO cidadeOutDTO = modelMapper.map(cidade, CidadeOutDTO.class);
        cidadeOutDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(CidadeController.class).buscar(cidadeOutDTO.getId())).withSelfRel());
        cidadeOutDTO.add(algaLinks.linkToCidades("cidades"));
        cidadeOutDTO.getEstado().add(algaLinks.linkToEstado(cidadeOutDTO.getEstado().getId()));
        return cidadeOutDTO;
    }

    @Override
    public CollectionModel<CidadeOutDTO> toCollectionModel(Iterable<? extends Cidade> cidades) {
        return super.toCollectionModel(cidades)
                .add(algaLinks.linkToCidades());
    }
}
