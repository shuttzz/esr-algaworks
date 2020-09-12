package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.GrupoDTOAssembler;
import br.com.badbit.algafoods.api.assembler.GrupoInputDisassembler;
import br.com.badbit.algafoods.api.model.input.GrupoInDTO;
import br.com.badbit.algafoods.api.model.output.GrupoOutDTO;
import br.com.badbit.algafoods.domain.model.Grupo;
import br.com.badbit.algafoods.domain.repository.GrupoRepository;
import br.com.badbit.algafoods.domain.service.CadastroGrupoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private GrupoRepository grupoRepository;
    private CadastroGrupoService cadastroGrupoService;
    private GrupoDTOAssembler grupoDTOAssembler;
    private GrupoInputDisassembler grupoInputDisassembler;

    public GrupoController(GrupoRepository grupoRepository, CadastroGrupoService cadastroGrupoService, GrupoDTOAssembler grupoDTOAssembler, GrupoInputDisassembler grupoInputDisassembler) {
        this.grupoRepository = grupoRepository;
        this.cadastroGrupoService = cadastroGrupoService;
        this.grupoDTOAssembler = grupoDTOAssembler;
        this.grupoInputDisassembler = grupoInputDisassembler;
    }

    @GetMapping
    public List<GrupoOutDTO> listar() {
        List<Grupo> grupos = grupoRepository.findAll();
        return grupoDTOAssembler.toCollectionDTO(grupos);
    }

    @GetMapping("/{grupoId}")
    public GrupoOutDTO buscar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
        return grupoDTOAssembler.toDTO(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoOutDTO salvar(@RequestBody @Valid GrupoInDTO grupoInDTO) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInDTO);
        grupo = cadastroGrupoService.salvar(grupo);
        return grupoDTOAssembler.toDTO(grupo);
    }

    @PutMapping("/{grupoId}")
    public GrupoOutDTO atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInDTO grupoInDTO) {
        Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);

        grupoInputDisassembler.copyToDomainObject(grupoInDTO, grupoAtual);
        grupoAtual = cadastroGrupoService.salvar(grupoAtual);

        return grupoDTOAssembler.toDTO(grupoAtual);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long grupoId) {
        cadastroGrupoService.excluir(grupoId);
    }

}
