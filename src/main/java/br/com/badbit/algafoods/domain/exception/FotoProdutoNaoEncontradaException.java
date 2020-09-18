package br.com.badbit.algafoods.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public FotoProdutoNaoEncontradaException(Long restauranteId, Long produtoId) {
        super(String.format("Não existe um cadastro de foto do produto com id %d para o restaurante de id %d",
                produtoId, restauranteId));
    }

}
