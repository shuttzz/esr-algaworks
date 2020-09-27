package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.controller.PedidoController;
import br.com.badbit.algafoods.api.controller.RestauranteController;
import br.com.badbit.algafoods.api.controller.UsuarioController;
import br.com.badbit.algafoods.api.model.output.PedidoResumoOutDTO;
import br.com.badbit.algafoods.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoResumoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoOutDTO> {

    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public PedidoResumoDTOAssembler(ModelMapper modelMapper) {
        super(PedidoController.class, PedidoResumoOutDTO.class);
        this.modelMapper = modelMapper;
    }

    @Override
    public CollectionModel<PedidoResumoOutDTO> toCollectionModel(Iterable<? extends Pedido> pedidos) {
        return super.toCollectionModel(pedidos)
                .add(linkTo(PedidoController.class).withSelfRel());
    }

    @Override
    public PedidoResumoOutDTO toModel(Pedido pedido) {
        PedidoResumoOutDTO pedidoResumoOutDTO = modelMapper.map(pedido, PedidoResumoOutDTO.class);

        pedidoResumoOutDTO.add(linkTo(WebMvcLinkBuilder
                .methodOn(PedidoController.class).buscar(pedidoResumoOutDTO.getCodigo())).withSelfRel());
        pedidoResumoOutDTO.add(algaLinks.linkToPedidos("pedidos"));
        pedidoResumoOutDTO.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
        pedidoResumoOutDTO.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));

        return pedidoResumoOutDTO;
    }
}
