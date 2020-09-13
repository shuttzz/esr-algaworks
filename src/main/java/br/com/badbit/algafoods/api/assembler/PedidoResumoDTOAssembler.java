package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.output.PedidoResumoOutDTO;
import br.com.badbit.algafoods.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoDTOAssembler {

    private ModelMapper modelMapper;

    public PedidoResumoDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PedidoResumoOutDTO toDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResumoOutDTO.class);
    }

    public List<PedidoResumoOutDTO> toCollectionDTO(Collection<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> toDTO(pedido))
                .collect(Collectors.toList());
    }

}
