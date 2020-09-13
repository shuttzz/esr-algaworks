package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.domain.service.FluxoStatusPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/pedidos/{codigoPedido}")
public class FluxoStatusPedidoController {

    private FluxoStatusPedidoService fluxoStatusPedidoService;

    public FluxoStatusPedidoController(FluxoStatusPedidoService fluxoStatusPedidoService) {
        this.fluxoStatusPedidoService = fluxoStatusPedidoService;
    }

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable UUID codigoPedido) {
        fluxoStatusPedidoService.confirmar(codigoPedido);
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable UUID codigoPedido) {
        fluxoStatusPedidoService.cancelar(codigoPedido);
    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar(@PathVariable UUID codigoPedido) {
        fluxoStatusPedidoService.entregar(codigoPedido);
    }

}
