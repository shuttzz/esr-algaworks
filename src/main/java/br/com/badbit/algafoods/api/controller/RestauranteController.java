package br.com.badbit.algafoods.api.controller;

import java.util.List;

import javax.validation.Valid;

import br.com.badbit.algafoods.domain.exception.CidadeNaoEncontradaException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.badbit.algafoods.api.assembler.RestauranteDTOAssembler;
import br.com.badbit.algafoods.api.assembler.RestauranteInputDisassembler;
import br.com.badbit.algafoods.api.model.input.RestauranteInDTO;
import br.com.badbit.algafoods.api.model.output.RestauranteOutDTO;
import br.com.badbit.algafoods.domain.exception.CozinhaNaoEncontradaException;
import br.com.badbit.algafoods.domain.exception.NegocioException;
import br.com.badbit.algafoods.domain.model.Restaurante;
import br.com.badbit.algafoods.domain.repository.RestauranteRepository;
import br.com.badbit.algafoods.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private RestauranteRepository restauranteRepository;
    private CadastroRestauranteService cadastroRestauranteService;
    private RestauranteDTOAssembler restauranteDTOAssembler;
    private RestauranteInputDisassembler restauranteModelDisassembler;
    // private SmartValidator validator;

    public RestauranteController(RestauranteRepository restauranteRepository,
            CadastroRestauranteService cadastroRestauranteService, RestauranteDTOAssembler restauranteDTOAssembler,
            RestauranteInputDisassembler restauranteModelDisassembler) {
        this.restauranteRepository = restauranteRepository;
        this.cadastroRestauranteService = cadastroRestauranteService;
        this.restauranteDTOAssembler = restauranteDTOAssembler;
        this.restauranteModelDisassembler = restauranteModelDisassembler;
    }

    @GetMapping
    public List<RestauranteOutDTO> listar() {
        return restauranteDTOAssembler.toCollectionDTO(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteOutDTO buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        
        return restauranteDTOAssembler.toDTO(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteOutDTO salvar(@RequestBody @Valid RestauranteInDTO restauranteInDTO) {
        try {
            Restaurante restaurante = restauranteModelDisassembler.toDomainObject(restauranteInDTO);
            return restauranteDTOAssembler.toDTO(cadastroRestauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteOutDTO atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInDTO restauranteInDTO) {
        try {
            Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);
            restauranteModelDisassembler.copyToDomainObject(restauranteInDTO, restauranteAtual);
            return restauranteDTOAssembler.toDTO(cadastroRestauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.inativar(restauranteId);
    }

    /* @PatchMapping("/{restauranteId}")
    public RestauranteOutDTO  atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos,
                                        HttpServletRequest request) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        merge(campos, restaurante, request);
        validate(restaurante, "restaurante");

        return atualizar(restauranteId, restaurante);
    } */

    /* private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
        validator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidacaoException(bindingResult);
        }
    } */

    /* private void merge(Map<String, Object> dadosOrigem, RestauranteInDTO restauranteDestino, HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            RestauranteInDTO restauranteOrigem = objectMapper.convertValue(dadosOrigem, RestauranteInDTO.class);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    } */

}
