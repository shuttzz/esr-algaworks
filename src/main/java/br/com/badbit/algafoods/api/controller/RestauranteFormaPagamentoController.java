package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.assembler.FormaPagamentoDTOAssembler;
import br.com.badbit.algafoods.api.model.output.FormaPagamentoOutDTO;
import br.com.badbit.algafoods.domain.model.Restaurante;
import br.com.badbit.algafoods.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

    private CadastroRestauranteService cadastroRestauranteService;
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    public RestauranteFormaPagamentoController(CadastroRestauranteService cadastroRestauranteService, FormaPagamentoDTOAssembler formaPagamentoDTOAssembler) {
        this.cadastroRestauranteService = cadastroRestauranteService;
        this.formaPagamentoDTOAssembler = formaPagamentoDTOAssembler;
    }

    @GetMapping
    public CollectionModel<FormaPagamentoOutDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        CollectionModel<FormaPagamentoOutDTO> formasPagamentoOutDTO = formaPagamentoDTOAssembler.toCollectionModel(restaurante.getFormasPagamento());

        formasPagamentoOutDTO.getContent().forEach(formaPagamentoOutDTO -> {
            formaPagamentoOutDTO
                    .add(algaLinks.linkToRestauranteFormaPagamentoDesassociacao(
                            restauranteId, formaPagamentoOutDTO.getId(), "desassociar"))
                    .add(algaLinks.linkToRestauranteFormaPagamentoAssociacao(restauranteId, "associar"));
        });

        return formasPagamentoOutDTO;
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        cadastroRestauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        cadastroRestauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);

        return ResponseEntity.noContent().build();
    }

}
