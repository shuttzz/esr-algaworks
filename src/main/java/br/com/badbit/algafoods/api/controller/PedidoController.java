package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.PedidoDTOAssembler;
import br.com.badbit.algafoods.api.assembler.PedidoInputDisassembler;
import br.com.badbit.algafoods.api.assembler.PedidoResumoDTOAssembler;
import br.com.badbit.algafoods.api.model.input.PedidoInDTO;
import br.com.badbit.algafoods.api.model.output.PedidoOutDTO;
import br.com.badbit.algafoods.api.model.output.PedidoResumoOutDTO;
import br.com.badbit.algafoods.domain.exception.EntidadeNaoEncontradaException;
import br.com.badbit.algafoods.domain.exception.NegocioException;
import br.com.badbit.algafoods.domain.model.Pedido;
import br.com.badbit.algafoods.domain.model.Usuario;
import br.com.badbit.algafoods.domain.repository.PedidoRepository;
import br.com.badbit.algafoods.domain.service.EmissaoPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    private PedidoRepository pedidoRepository;
    private EmissaoPedidoService emissaoPedidoService;
    private PedidoDTOAssembler pedidoDTOAssembler;
    private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;
    private PedidoInputDisassembler pedidoInputDisassembler;

    public PedidoController(PedidoRepository pedidoRepository, EmissaoPedidoService emissaoPedidoService,
                            PedidoDTOAssembler pedidoDTOAssembler, PedidoResumoDTOAssembler pedidoResumoDTOAssembler,
                            PedidoInputDisassembler pedidoInputDisassembler) {
        this.pedidoRepository = pedidoRepository;
        this.emissaoPedidoService = emissaoPedidoService;
        this.pedidoDTOAssembler = pedidoDTOAssembler;
        this.pedidoResumoDTOAssembler = pedidoResumoDTOAssembler;
        this.pedidoInputDisassembler = pedidoInputDisassembler;
    }

    @GetMapping
    public List<PedidoResumoOutDTO> listar() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();

        return pedidoResumoDTOAssembler.toCollectionDTO(todosPedidos);
    }

    @GetMapping("/{codigoPedido}")
    public PedidoOutDTO buscar(@PathVariable UUID codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

        return pedidoDTOAssembler.toDTO(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoOutDTO adicionar(@Valid @RequestBody PedidoInDTO pedidoInDTO) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInDTO);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedidoService.emitir(novoPedido);

            return pedidoDTOAssembler.toDTO(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

}
