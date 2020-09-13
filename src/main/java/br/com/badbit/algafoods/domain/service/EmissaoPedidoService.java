package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.exception.NegocioException;
import br.com.badbit.algafoods.domain.exception.PedidoNaoEncontradoException;
import br.com.badbit.algafoods.domain.model.*;
import br.com.badbit.algafoods.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class EmissaoPedidoService {

    private PedidoRepository pedidoRepository;
    private CadastroRestauranteService cadastroRestauranteService;
    private CadastroCidadeService cadastroCidadeService;
    private CadastroUsuarioService cadastroUsuarioService;
    private CadastroProdutoService cadastroProdutoService;
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;


    public EmissaoPedidoService(PedidoRepository pedidoRepository, CadastroRestauranteService cadastroRestauranteService,
                                CadastroCidadeService cadastroCidadeService, CadastroUsuarioService cadastroUsuarioService,
                                CadastroProdutoService cadastroProdutoService, CadastroFormaPagamentoService cadastroFormaPagamentoService) {
        this.pedidoRepository = pedidoRepository;
        this.cadastroRestauranteService = cadastroRestauranteService;
        this.cadastroCidadeService = cadastroCidadeService;
        this.cadastroUsuarioService = cadastroUsuarioService;
        this.cadastroProdutoService = cadastroProdutoService;
        this.cadastroFormaPagamentoService = cadastroFormaPagamentoService;
    }

    @Transactional
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    public Pedido buscarOuFalhar(UUID codigoPedido) {
        return pedidoRepository.findByCodigo(codigoPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
    }

    private void validarPedido(Pedido pedido) {
        Cidade cidade = cadastroCidadeService.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
        Usuario cliente = cadastroUsuarioService.buscarOuFalhar(pedido.getCliente().getId());
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento %s não é aceita por este restaurante", formaPagamento.getDescricao()));
        }
    }

    private void validarItens(Pedido pedido) {
        pedido.getItensPedido().forEach(item -> {
            Produto produto = cadastroProdutoService.buscarOuFalhar(pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }

}
