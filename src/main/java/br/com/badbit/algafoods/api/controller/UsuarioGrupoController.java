package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.assembler.GrupoDTOAssembler;
import br.com.badbit.algafoods.api.model.output.GrupoOutDTO;
import br.com.badbit.algafoods.domain.model.Usuario;
import br.com.badbit.algafoods.domain.service.CadastroUsuarioService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    private CadastroUsuarioService cadastroUsuarioService;
    private GrupoDTOAssembler grupoDTOAssembler;
    private final AlgaLinks algaLinks;

    public UsuarioGrupoController(CadastroUsuarioService cadastroUsuarioService, GrupoDTOAssembler grupoDTOAssembler, AlgaLinks algaLinks) {
        this.cadastroUsuarioService = cadastroUsuarioService;
        this.grupoDTOAssembler = grupoDTOAssembler;
        this.algaLinks = algaLinks;
    }

    @GetMapping
    public CollectionModel<GrupoOutDTO> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        CollectionModel<GrupoOutDTO> gruposModel = grupoDTOAssembler.toCollectionModel(usuario.getGrupos())
                .removeLinks()
                .add(algaLinks.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));

        gruposModel.getContent().forEach(grupoModel -> {
            grupoModel.add(algaLinks.linkToUsuarioGrupoDesassociacao(
                    usuarioId, grupoModel.getId(), "desassociar"));
        });

        return gruposModel;
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

}
