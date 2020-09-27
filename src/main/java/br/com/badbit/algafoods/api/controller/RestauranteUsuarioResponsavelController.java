package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.assembler.UsuarioDTOAssembler;
import br.com.badbit.algafoods.api.model.output.UsuarioOutDTO;
import br.com.badbit.algafoods.domain.model.Restaurante;
import br.com.badbit.algafoods.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    private CadastroRestauranteService cadastroRestauranteService;
    private UsuarioDTOAssembler usuarioDTOAssembler;
    private final AlgaLinks algaLinks;

    public RestauranteUsuarioResponsavelController(CadastroRestauranteService cadastroRestauranteService, UsuarioDTOAssembler usuarioDTOAssembler, AlgaLinks algaLinks) {
        this.cadastroRestauranteService = cadastroRestauranteService;
        this.usuarioDTOAssembler = usuarioDTOAssembler;
        this.algaLinks = algaLinks;
    }

    @GetMapping
    public CollectionModel<UsuarioOutDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        return usuarioDTOAssembler.toCollectionModel(restaurante.getResponsaveis())
                .removeLinks() // Retira os links dessa coleção, pois está retornando um link para /usuarios e não para a coleção /restaurantes/id_aqui/usuarios
                .add(algaLinks.linkToResponsaveisRestaurante(restauranteId));
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.desassociarResponsavel(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.associarResponsavel(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }

}
