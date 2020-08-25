package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.exception.EntidadeNaoEncontradaException;
import br.com.badbit.algafoods.domain.model.Cidade;
import br.com.badbit.algafoods.domain.model.Estado;
import br.com.badbit.algafoods.domain.repository.CidadeRepository;
import br.com.badbit.algafoods.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadeService {

    public CidadeRepository cidadeRepository;
    public EstadoRepository estadoRepository;

    public CadastroCidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(
                "Não existe um estado com o id %d", estadoId)));

        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);

    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma cidade com o id %d",
                    cidadeId));
        }
    }
}
