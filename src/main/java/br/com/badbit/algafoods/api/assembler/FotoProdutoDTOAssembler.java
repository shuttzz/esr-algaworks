package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.controller.RestauranteProdutoFotoController;
import br.com.badbit.algafoods.api.model.output.FotoProdutoOutDTO;
import br.com.badbit.algafoods.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoDTOAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoOutDTO> {

    private ModelMapper modelMapper;
    private final AlgaLinks algaLinks;

    public FotoProdutoDTOAssembler(ModelMapper modelMapper, AlgaLinks algaLinks) {
        super(RestauranteProdutoFotoController.class, FotoProdutoOutDTO.class);
        this.modelMapper = modelMapper;
        this.algaLinks = algaLinks;
    }

    @Override
    public FotoProdutoOutDTO toModel(FotoProduto foto) {
        FotoProdutoOutDTO fotoProdutoModel = modelMapper.map(foto, FotoProdutoOutDTO.class);

        fotoProdutoModel.add(algaLinks.linkToFotoProduto(
                foto.getRestauranteId(), foto.getProduto().getId()));

        fotoProdutoModel.add(algaLinks.linkToProduto(
                foto.getRestauranteId(), foto.getProduto().getId(), "produto"));

        return fotoProdutoModel;
    }

}
