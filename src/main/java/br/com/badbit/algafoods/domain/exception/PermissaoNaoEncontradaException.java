package br.com.badbit.algafoods.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public PermissaoNaoEncontradaException(String message) {
        super(message);
    }

    public PermissaoNaoEncontradaException(Long id) {
        this(String.format("Não foi encontrado nenhuma permissão com o id %d", id));
    }

}
