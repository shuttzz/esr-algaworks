package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.model.Cidade;
import br.com.badbit.algafoods.domain.model.FormaPagamento;
import org.springframework.stereotype.Service;

import br.com.badbit.algafoods.domain.exception.RestauranteNaoEncontradoException;
import br.com.badbit.algafoods.domain.model.Cozinha;
import br.com.badbit.algafoods.domain.model.Restaurante;
import br.com.badbit.algafoods.domain.repository.RestauranteRepository;

import javax.transaction.Transactional;

@Service
public class CadastroRestauranteService {

    private RestauranteRepository restauranteRepository;
    private CadastroCozinhaService cadastroCozinhaService;
    private CadastroCidadeService cadastroCidadeService;
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    public CadastroRestauranteService(RestauranteRepository restauranteRepository,
                                      CadastroCozinhaService cadastroCozinhaService, CadastroCidadeService cadastroCidadeService,
                                      CadastroFormaPagamentoService cadastroFormaPagamentoService) {
        this.restauranteRepository = restauranteRepository;
        this.cadastroCozinhaService = cadastroCozinhaService;
        this.cadastroCidadeService = cadastroCidadeService;
        this.cadastroFormaPagamentoService = cadastroFormaPagamentoService;
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
        Cidade cidade = cadastroCidadeService.buscarOuFalhar(restaurante.getEndereco().getCidade().getId());
        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);

        restaurante.ativar(); // Não preciso dá um save, pois a entidade está sendo gerenciada pelo JPA
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);

        restaurante.inativar(); // Não preciso dá um save, pois a entidade está sendo gerenciada pelo JPA
    }

    @Transactional
    public void desassociarFormaPagamento(Long resturanteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(resturanteId);
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void associarFormaPagamento(Long resturanteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(resturanteId);
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new RestauranteNaoEncontradoException(restauranteId));
    }
}
