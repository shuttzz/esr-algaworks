package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.exception.EntidadeEmUsoException;
import br.com.badbit.algafoods.domain.exception.EntidadeNaoEncontradaException;
import br.com.badbit.algafoods.domain.model.Estado;
import br.com.badbit.algafoods.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    private EstadoRepository estadoRepository;

    public CadastroEstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum estado com o id %d",
                    estadoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("O estado de id %d não pode ser removido, pois está " +
                    "vinculado à uma cidade", estadoId));
        }
    }
}
