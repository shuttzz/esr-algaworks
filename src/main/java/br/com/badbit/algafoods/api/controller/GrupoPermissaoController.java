package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.assembler.PermissaoDTOAssembler;
import br.com.badbit.algafoods.api.model.output.PermissaoOutDTO;
import br.com.badbit.algafoods.domain.model.Grupo;
import br.com.badbit.algafoods.domain.service.CadastroGrupoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    private CadastroGrupoService cadastroGrupoService;
    private PermissaoDTOAssembler permissaoDTOAssembler;
    private final AlgaLinks algaLinks;

    public GrupoPermissaoController(CadastroGrupoService cadastroGrupoService, PermissaoDTOAssembler permissaoDTOAssembler, AlgaLinks algaLinks) {
        this.cadastroGrupoService = cadastroGrupoService;
        this.permissaoDTOAssembler = permissaoDTOAssembler;
        this.algaLinks = algaLinks;
    }

    @GetMapping
    public CollectionModel<PermissaoOutDTO> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        CollectionModel<PermissaoOutDTO> permissoesModel
                = permissaoDTOAssembler.toCollectionModel(grupo.getPermissoes())
                .removeLinks()
                .add(algaLinks.linkToGrupoPermissoes(grupoId))
                .add(algaLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

        permissoesModel.getContent().forEach(permissaoModel -> {
            permissaoModel.add(algaLinks.linkToGrupoPermissaoDesassociacao(
                    grupoId, permissaoModel.getId(), "desassociar"));
        });

        return permissoesModel;
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.desassociarPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.associarPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }

}
