package br.com.badbit.algafoods.integration;

import br.com.badbit.algafoods.domain.exception.CozinhaNaoEncontradaException;
import br.com.badbit.algafoods.domain.model.Cozinha;
import br.com.badbit.algafoods.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CadastroCozinhaIT {

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Test
    public void testarCadastroCozinhaComSucesso() {
        // Cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        // ação
        novaCozinha = cadastroCozinhaService.salvar(novaCozinha);

        // validação
        assertNotNull(novaCozinha);
        assertNotNull(novaCozinha.getId());
    }

    @Test
    public void testarCadastroCozinhaSemNome() {
        assertThrows(TransactionSystemException.class,
                () -> {
                    Cozinha novaCozinha = new Cozinha();
                    novaCozinha.setNome(null);
                    novaCozinha = cadastroCozinhaService.salvar(novaCozinha);
                });
    }

    @Test
    public void testarExcluirCozinhaEmUso() {
        assertThrows(TransactionSystemException.class,
                () -> {
                    cadastroCozinhaService.excluir(1L);
                });
    }

    @Test
    public void testarExcluirEntidadeNaoExistente() {
        assertThrows(CozinhaNaoEncontradaException.class,
                () -> {
                    cadastroCozinhaService.excluir(100L);
                });
    }

}
