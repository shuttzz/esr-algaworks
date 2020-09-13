package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.GrupoDTOAssembler;
import br.com.badbit.algafoods.api.model.input.GrupoInDTO;
import br.com.badbit.algafoods.api.model.output.GrupoOutDTO;
import br.com.badbit.algafoods.domain.model.Grupo;
import br.com.badbit.algafoods.domain.model.Usuario;
import br.com.badbit.algafoods.domain.service.CadastroUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    private CadastroUsuarioService cadastroUsuarioService;
    private GrupoDTOAssembler grupoDTOAssembler;

    public UsuarioGrupoController(CadastroUsuarioService cadastroUsuarioService, GrupoDTOAssembler grupoDTOAssembler) {
        this.cadastroUsuarioService = cadastroUsuarioService;
        this.grupoDTOAssembler = grupoDTOAssembler;
    }

    @GetMapping
    public List<GrupoOutDTO> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        return grupoDTOAssembler.toCollectionDTO(usuario.getGrupos());
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
    }

}
