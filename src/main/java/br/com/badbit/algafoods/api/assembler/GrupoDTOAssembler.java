package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.controller.GrupoController;
import br.com.badbit.algafoods.api.model.output.GrupoOutDTO;
import br.com.badbit.algafoods.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GrupoDTOAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoOutDTO> {

    private ModelMapper modelMapper;
    private final AlgaLinks algaLinks;

    public GrupoDTOAssembler(ModelMapper modelMapper, AlgaLinks algaLinks) {
        super(GrupoController.class, GrupoOutDTO.class);
        this.modelMapper = modelMapper;
        this.algaLinks = algaLinks;
    }

    @Override
    public GrupoOutDTO toModel(Grupo grupo) {
        GrupoOutDTO grupoModel = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoModel);

        grupoModel.add(algaLinks.linkToGrupos("grupos"));

        grupoModel.add(algaLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));

        return grupoModel;
    }

    @Override
    public CollectionModel<GrupoOutDTO> toCollectionModel(Iterable<? extends Grupo> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToGrupos());
    }

}
