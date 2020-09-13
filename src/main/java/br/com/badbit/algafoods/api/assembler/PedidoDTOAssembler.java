package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.output.PedidoOutDTO;
import br.com.badbit.algafoods.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoDTOAssembler {

    private ModelMapper modelMapper;

    public PedidoDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PedidoOutDTO toDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoOutDTO.class);
    }

    public List<PedidoOutDTO> toCollectionDTO(Collection<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> toDTO(pedido))
                .collect(Collectors.toList());
    }

}
