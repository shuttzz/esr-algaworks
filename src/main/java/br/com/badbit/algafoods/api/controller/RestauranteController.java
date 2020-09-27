package br.com.badbit.algafoods.api.controller;

import java.util.List;

import javax.validation.Valid;

import br.com.badbit.algafoods.api.assembler.RestauranteApenasNomeModelAssembler;
import br.com.badbit.algafoods.api.assembler.RestauranteBasicoModelAssembler;
import br.com.badbit.algafoods.api.model.output.RestauranteApenasNomeModel;
import br.com.badbit.algafoods.api.model.output.RestauranteBasicoModel;
import br.com.badbit.algafoods.domain.exception.CidadeNaoEncontradaException;
import br.com.badbit.algafoods.domain.exception.RestauranteNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private RestauranteBasicoModelAssembler restauranteBasicoModelAssembler;
    @Autowired
    private RestauranteApenasNomeModelAssembler restauranteApenasNomeModelAssembler;
    // private SmartValidator validator;

    public RestauranteController(RestauranteRepository restauranteRepository,
            CadastroRestauranteService cadastroRestauranteService, RestauranteDTOAssembler restauranteDTOAssembler,
            RestauranteInputDisassembler restauranteModelDisassembler) {
        this.restauranteRepository = restauranteRepository;
        this.cadastroRestauranteService = cadastroRestauranteService;
        this.restauranteDTOAssembler = restauranteDTOAssembler;
        this.restauranteModelDisassembler = restauranteModelDisassembler;
    }

//    @JsonView(RestauranteView.Resumo.class)
    @GetMapping
    public CollectionModel<RestauranteBasicoModel> listar() {
        return restauranteBasicoModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

//	@JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome")
    public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes() {
        return restauranteApenasNomeModelAssembler
                .toCollectionModel(restauranteRepository.findAll());
    }

//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {
//        List<Restaurante> restaurantes = restauranteRepository.findAll();
//
//        List<RestauranteOutDTO> restaurantesModel = restauranteDTOAssembler.toCollectionDTO(restaurantes);
//
//        MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantesModel);
//        if ("resumo".equals(projecao)) {
//            restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//        }
//
//        return restaurantesWrapper;
//    }

//    @GetMapping
//    public List<RestauranteOutDTO> listar() {
//        return restauranteDTOAssembler.toCollectionDTO(restauranteRepository.findAll());
//    }
//
//    @JsonView(RestauranteView.Resumo.class)
//    @GetMapping(params = "projecao=resumo")
//    public List<RestauranteOutDTO> listarResumido() {
//        return listar();
//    }

    @GetMapping("/{restauranteId}")
    public RestauranteOutDTO buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        
        return restauranteDTOAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteOutDTO salvar(@RequestBody @Valid RestauranteInDTO restauranteInDTO) {
        try {
            Restaurante restaurante = restauranteModelDisassembler.toDomainObject(restauranteInDTO);
            return restauranteDTOAssembler.toModel(cadastroRestauranteService.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteOutDTO atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInDTO restauranteInDTO) {
        try {
            Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);
            restauranteModelDisassembler.copyToDomainObject(restauranteInDTO, restauranteAtual);
            return restauranteDTOAssembler.toModel(cadastroRestauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.ativar(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            cadastroRestauranteService.ativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException exception) {
            throw new NegocioException(exception.getMessage(), exception);
        }
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.inativar(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            cadastroRestauranteService.inativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException exception) {
            throw new NegocioException(exception.getMessage(), exception);
        }
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
        cadastroRestauranteService.abrir(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
        cadastroRestauranteService.fechar(restauranteId);
        return ResponseEntity.noContent().build();
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
