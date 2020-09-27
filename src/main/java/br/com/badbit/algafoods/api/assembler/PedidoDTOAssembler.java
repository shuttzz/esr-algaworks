package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.controller.*;
import br.com.badbit.algafoods.api.model.output.PedidoOutDTO;
import br.com.badbit.algafoods.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoOutDTO> {

    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public PedidoDTOAssembler(ModelMapper modelMapper) {
        super(PedidoController.class, PedidoOutDTO.class);
        this.modelMapper = modelMapper;
    }

    @Override
    public CollectionModel<PedidoOutDTO> toCollectionModel(Iterable<? extends Pedido> pedidos) {
        return super.toCollectionModel(pedidos)
                .add(linkTo(PedidoController.class).withSelfRel());
    }

    @Override
    public PedidoOutDTO toModel(Pedido pedido) {
        PedidoOutDTO pedidoOutDTO = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoOutDTO);

        pedidoOutDTO.add(algaLinks.linkToPedidos());

        if (pedido.podeSerConfirmado()) {
            pedidoOutDTO.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
        }

        if (pedido.podeSerCancelado()) {
            pedidoOutDTO.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelamento"));
        }

        if (pedido.podeSerEntregue()) {
            pedidoOutDTO.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
        }

        pedidoOutDTO.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoOutDTO.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));

        pedidoOutDTO.getFormaPagamento().add(algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

        pedidoOutDTO.getEnderecoEntrega().getCidade().add(algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

        pedidoOutDTO.getItens().forEach(item -> {
            item.add(linkTo(methodOn(RestauranteProdutoController.class)
                    .buscar(pedidoOutDTO.getRestaurante().getId(), item.getId()))
                    .withRel("produto"));
        });

        return pedidoOutDTO;
    }

}
