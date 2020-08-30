package br.com.badbit.algafoods.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CidadeNaoEncontradaException(String message) {
        super(message);
    }

    public CidadeNaoEncontradaException(Long id) {
        this(String.format("Não foi encontrada nenhuma cidade com o id %d", id));
    }

}
