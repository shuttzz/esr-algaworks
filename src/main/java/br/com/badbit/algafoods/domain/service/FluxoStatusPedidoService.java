package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.model.Pedido;
import br.com.badbit.algafoods.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class FluxoStatusPedidoService {

    private EmissaoPedidoService emissaoPedidoService;
    private PedidoRepository pedidoRepository;

    public FluxoStatusPedidoService(EmissaoPedidoService emissaoPedidoService, PedidoRepository pedidoRepository) {
        this.emissaoPedidoService = emissaoPedidoService;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public void confirmar(UUID codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.confirmar();

        // Não precisaria disso, pois o confirmar já salvaria o pedido, porém para funcionar a questão do evento
        // somos obrigado a chamar o save do repository, sendo que esse repository precisa ser do spring data para funcionar
        pedidoRepository.save(pedido);
    }

    @Transactional
    public void cancelar(UUID codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.cancelar();

        pedidoRepository.save(pedido);
    }

    @Transactional
    public void entregar(UUID codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.entregar();
    }

}
