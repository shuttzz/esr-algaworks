package br.com.badbit.algafoods.domain.service;

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

    public CadastroRestauranteService(RestauranteRepository restauranteRepository, CadastroCozinhaService cadastroCozinhaService) {
        this.restauranteRepository = restauranteRepository;
        this.cadastroCozinhaService = cadastroCozinhaService;
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new RestauranteNaoEncontradoException(restauranteId));
    }
}
