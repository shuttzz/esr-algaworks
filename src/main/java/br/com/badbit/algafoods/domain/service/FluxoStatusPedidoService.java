package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.model.Pedido;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class FluxoStatusPedidoService {

    private EmissaoPedidoService emissaoPedidoService;

    public FluxoStatusPedidoService(EmissaoPedidoService emissaoPedidoService) {
        this.emissaoPedidoService = emissaoPedidoService;
    }

    @Transactional
    public void confirmar(UUID codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.confirmar();
    }

    @Transactional
    public void cancelar(UUID codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.cancelar();
    }

    @Transactional
    public void entregar(UUID codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.entregar();
    }

}
