package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.domain.service.FluxoStatusPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> confirmar(@PathVariable UUID codigoPedido) {
        fluxoStatusPedidoService.confirmar(codigoPedido);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancelar(@PathVariable UUID codigoPedido) {
        fluxoStatusPedidoService.cancelar(codigoPedido);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> entregar(@PathVariable UUID codigoPedido) {
        fluxoStatusPedidoService.entregar(codigoPedido);
        return ResponseEntity.noContent().build();
    }

}
