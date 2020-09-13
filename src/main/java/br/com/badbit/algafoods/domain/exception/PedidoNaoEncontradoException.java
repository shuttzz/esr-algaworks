package br.com.badbit.algafoods.domain.exception;

import java.util.UUID;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoException(UUID codigoPedido) {
        super(String.format("Não foi encontrado nenhum pedido com o id %d", codigoPedido));
    }

}
