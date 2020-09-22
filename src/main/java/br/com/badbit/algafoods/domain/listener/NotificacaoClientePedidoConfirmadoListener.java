package br.com.badbit.algafoods.domain.listener;

import br.com.badbit.algafoods.domain.event.PedidoConfirmadoEvent;
import br.com.badbit.algafoods.domain.model.Pedido;
import br.com.badbit.algafoods.domain.service.EnvioEmailService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

    private EnvioEmailService envioEmailService;

    public NotificacaoClientePedidoConfirmadoListener(EnvioEmailService envioEmailService) {
        this.envioEmailService = envioEmailService;
    }

    @EventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        Pedido pedido = event.getPedido();
        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();
        envioEmailService.enviar(mensagem);
    }

}
