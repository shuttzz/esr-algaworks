package br.com.badbit.algafoods.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public EstadoNaoEncontradoException(String message) {
        super(message);
    }

    public EstadoNaoEncontradoException(Long id) {
        this(String.format("Não foi encontrado nenhum estado com o id %d", id));
    }

}
