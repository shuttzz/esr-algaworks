package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.exception.EntidadeNaoEncontradaException;
import br.com.badbit.algafoods.domain.model.Cozinha;
import br.com.badbit.algafoods.domain.model.Restaurante;
import br.com.badbit.algafoods.domain.repository.CozinhaRepository;
import br.com.badbit.algafoods.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    private RestauranteRepository restauranteRepository;
    private CozinhaRepository cozinhaRepository;

    public CadastroRestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(
                "Não existe uma cozinha com o id %d", cozinhaId)));

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }
}
