package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.PedidoDTOAssembler;
import br.com.badbit.algafoods.api.assembler.PedidoInputDisassembler;
import br.com.badbit.algafoods.api.assembler.PedidoResumoDTOAssembler;
import br.com.badbit.algafoods.api.model.input.PedidoInDTO;
import br.com.badbit.algafoods.api.model.output.PedidoOutDTO;
import br.com.badbit.algafoods.api.model.output.PedidoResumoOutDTO;
import br.com.badbit.algafoods.core.data.PageWrapper;
import br.com.badbit.algafoods.core.data.PageableTranslator;
import br.com.badbit.algafoods.domain.exception.EntidadeNaoEncontradaException;
import br.com.badbit.algafoods.domain.exception.NegocioException;
import br.com.badbit.algafoods.domain.model.Pedido;
import br.com.badbit.algafoods.domain.model.Usuario;
import br.com.badbit.algafoods.domain.repository.PedidoRepository;
import br.com.badbit.algafoods.domain.filter.PedidoFilter;
import br.com.badbit.algafoods.domain.service.EmissaoPedidoService;
import br.com.badbit.algafoods.infrastructure.repository.spec.PedidoSpecs;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

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
    public PagedModel<PedidoResumoOutDTO> pesquisar(PedidoFilter filtro, Pageable pageable) {
        Pageable pageableTraduzido = traduzirPageable(pageable);
        Page<Pedido> todosPedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

        todosPedidosPage = new PageWrapper<>(todosPedidosPage.getContent(), pageable, todosPedidosPage.getTotalElements());

        return pagedResourcesAssembler.toModel(todosPedidosPage, pedidoResumoDTOAssembler);
    }

//    @GetMapping
//    public List<PedidoResumoOutDTO> listar() {
//        List<Pedido> todosPedidos = pedidoRepository.findAll();
//
//        return pedidoResumoDTOAssembler.toCollectionDTO(todosPedidos);
//    }

    @GetMapping("/{codigoPedido}")
    public PedidoOutDTO buscar(@PathVariable UUID codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

        return pedidoDTOAssembler.toModel(pedido);
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

            return pedidoDTOAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = ImmutableMap.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valorTotal"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }

}
