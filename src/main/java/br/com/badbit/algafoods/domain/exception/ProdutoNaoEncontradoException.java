package br.com.badbit.algafoods.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }

    public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
        this(String.format("Não foi encontrada nenhum produto com o id %d para o restaurante de id %d", produtoId, restauranteId));
    }

}
