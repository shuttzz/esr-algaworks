package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.model.output.PermissaoOutDTO;
import br.com.badbit.algafoods.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PermissaoDTOAssembler implements RepresentationModelAssembler<Permissao, PermissaoOutDTO> {

    private ModelMapper modelMapper;
    private final AlgaLinks algaLinks;

    public PermissaoDTOAssembler(ModelMapper modelMapper, AlgaLinks algaLinks) {
        this.modelMapper = modelMapper;
        this.algaLinks = algaLinks;
    }

    @Override
    public PermissaoOutDTO toModel(Permissao permissao) {
        PermissaoOutDTO permissaoModel = modelMapper.map(permissao, PermissaoOutDTO.class);
        return permissaoModel;
    }

    @Override
    public CollectionModel<PermissaoOutDTO> toCollectionModel(Iterable<? extends Permissao> permissoes) {
        return RepresentationModelAssembler.super.toCollectionModel(permissoes)
                .add(algaLinks.linkToPermissoes());
    }

}
