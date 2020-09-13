package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.FormaPagamentoDTOAssembler;
import br.com.badbit.algafoods.api.assembler.PermissaoDTOAssembler;
import br.com.badbit.algafoods.api.model.output.FormaPagamentoOutDTO;
import br.com.badbit.algafoods.api.model.output.PermissaoOutDTO;
import br.com.badbit.algafoods.domain.model.Grupo;
import br.com.badbit.algafoods.domain.model.Restaurante;
import br.com.badbit.algafoods.domain.service.CadastroGrupoService;
import br.com.badbit.algafoods.domain.service.CadastroRestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    private CadastroGrupoService cadastroGrupoService;
    private PermissaoDTOAssembler permissaoDTOAssembler;

    public GrupoPermissaoController(CadastroGrupoService cadastroGrupoService, PermissaoDTOAssembler permissaoDTOAssembler) {
        this.cadastroGrupoService = cadastroGrupoService;
        this.permissaoDTOAssembler = permissaoDTOAssembler;
    }

    @GetMapping
    public List<PermissaoOutDTO> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        return permissaoDTOAssembler.toCollectionDTO(grupo.getPermissoes());
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.desassociarPermissao(grupoId, permissaoId);
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.associarPermissao(grupoId, permissaoId);
    }

}
