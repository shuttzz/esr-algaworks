package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.input.PedidoInDTO;
import br.com.badbit.algafoods.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PedidoInputDisassembler {

    private ModelMapper modelMapper;

    public PedidoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Pedido toDomainObject(PedidoInDTO pedidoInDTO) {
        return modelMapper.map(pedidoInDTO, Pedido.class);
    }

    public void copyToDomainObject(PedidoInDTO pedidoInDTO, Pedido pedido) {
        modelMapper.map(pedidoInDTO, pedido);
    }

}
