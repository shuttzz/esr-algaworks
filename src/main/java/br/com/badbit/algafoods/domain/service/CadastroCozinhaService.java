package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.exception.CidadeNaoEncontradaException;
import br.com.badbit.algafoods.domain.exception.CozinhaNaoEncontradaException;
import br.com.badbit.algafoods.domain.exception.EntidadeEmUsoException;
import br.com.badbit.algafoods.domain.exception.EntidadeNaoEncontradaException;
import br.com.badbit.algafoods.domain.model.Cozinha;
import br.com.badbit.algafoods.domain.repository.CozinhaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroCozinhaService {

    public static final String MSG_COZINHA_EM_USO = "Cozinha de id %d não pode ser removida, pois está " +
            "vinculada a um restaurante";

    private CozinhaRepository cozinhaRepository;

    public CadastroCozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void excluir(Long cozinhaId) {
        try {
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException(cozinhaId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, cozinhaId));
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId).orElseThrow(
                () -> new CozinhaNaoEncontradaException(cozinhaId));
    }

}
