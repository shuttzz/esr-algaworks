package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.controller.RestauranteProdutoController;
import br.com.badbit.algafoods.api.model.output.ProdutoOutDTO;
import br.com.badbit.algafoods.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoDTOAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoOutDTO> {

    private ModelMapper modelMapper;
    private final AlgaLinks algaLinks;

    public ProdutoDTOAssembler(ModelMapper modelMapper, AlgaLinks algaLinks) {
        super(RestauranteProdutoController.class, ProdutoOutDTO.class);
        this.modelMapper = modelMapper;
        this.algaLinks = algaLinks;
    }

    @Override
    public ProdutoOutDTO toModel(Produto produto) {
        ProdutoOutDTO produtoModel = createModelWithId(
                produto.getId(), produto, produto.getRestaurante().getId());

        modelMapper.map(produto, produtoModel);

        produtoModel.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));

        produtoModel.add(algaLinks.linkToFotoProduto(
                produto.getRestaurante().getId(), produto.getId(), "foto"));

        return produtoModel;
    }

    @Override
    public CollectionModel<ProdutoOutDTO> toCollectionModel(Iterable<? extends Produto> produtos) {
        return super.toCollectionModel(produtos)
                .add(WebMvcLinkBuilder.linkTo(RestauranteProdutoController.class).withSelfRel());
    }

}
