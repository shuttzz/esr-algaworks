package br.com.badbit.algafoods.domain.listener;

import br.com.badbit.algafoods.domain.event.PedidoCanceladoEvent;
import br.com.badbit.algafoods.domain.model.Pedido;
import br.com.badbit.algafoods.domain.service.EnvioEmailService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoCanceladoListener {

    private EnvioEmailService envioEmailService;

    public NotificacaoClientePedidoCanceladoListener(EnvioEmailService envioEmailService) {
        this.envioEmailService = envioEmailService;
    }

    // Com essa anotação o evento está sendo executado antes de salvar a confirmação do pedido no banco de dados
    // o que não é o essencial, pois o cliente recebe e-mail de confirmado e ai dá um erro e o pedido não será confirmado
    // por isso a anotação @EventListener será trocada por @TransactionalEventListener.
    // Com o uso da phase = TransactionPahse.BEFORE_COMMIT se ocorrer um erro na hora de atualizar o pedido o e-mail não será enviado
    // e também se ocorrer um erro ao tentar enviar o e-mail o pedido também não será atualizado
//    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void aoCancelarPedido(PedidoCanceladoEvent event) {
        Pedido pedido = event.getPedido();
        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido cancelado")
                .corpo("pedido-cancelado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();
        envioEmailService.enviar(mensagem);
    }

}
