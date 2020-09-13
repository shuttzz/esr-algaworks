package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.UsuarioDTOAssembler;
import br.com.badbit.algafoods.api.model.output.UsuarioOutDTO;
import br.com.badbit.algafoods.domain.model.Restaurante;
import br.com.badbit.algafoods.domain.service.CadastroRestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    private CadastroRestauranteService cadastroRestauranteService;
    private UsuarioDTOAssembler usuarioDTOAssembler;

    public RestauranteUsuarioResponsavelController(CadastroRestauranteService cadastroRestauranteService, UsuarioDTOAssembler usuarioDTOAssembler) {
        this.cadastroRestauranteService = cadastroRestauranteService;
        this.usuarioDTOAssembler = usuarioDTOAssembler;
    }

    @GetMapping
    public List<UsuarioOutDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        return usuarioDTOAssembler.toCollectionDTO(restaurante.getResponsaveis());
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.desassociarResponsavel(restauranteId, usuarioId);
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.associarResponsavel(restauranteId, usuarioId);
    }

}
