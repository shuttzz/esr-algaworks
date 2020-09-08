package br.com.badbit.algafoods.domain.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.badbit.algafoods.domain.exception.CidadeNaoEncontradaException;
import br.com.badbit.algafoods.domain.exception.EntidadeEmUsoException;
import br.com.badbit.algafoods.domain.model.Cidade;
import br.com.badbit.algafoods.domain.model.Estado;
import br.com.badbit.algafoods.domain.repository.CidadeRepository;

import javax.transaction.Transactional;

@Service
public class CadastroCidadeService {

    public static final String MSG_CIDADE_EM_USO = "A cidade de id %d não pode ser removida, pois está vinculada a um endereço";
    public CidadeRepository cidadeRepository;
    public CadastroEstadoService cadastroEstadoService;

    public CadastroCidadeService(CidadeRepository cidadeRepository, CadastroEstadoService cadastroEstadoService) {
        this.cidadeRepository = cidadeRepository;
        this.cadastroEstadoService = cadastroEstadoService;
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, cidadeId));
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId).orElseThrow(
                () -> new CidadeNaoEncontradaException(cidadeId));
    }
}
