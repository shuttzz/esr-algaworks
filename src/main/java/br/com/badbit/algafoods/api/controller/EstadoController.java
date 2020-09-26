package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.EstadoDTOAssembler;
import br.com.badbit.algafoods.api.assembler.EstadoInputDisassembler;
import br.com.badbit.algafoods.api.model.input.EstadoInDTO;
import br.com.badbit.algafoods.api.model.output.EstadoOutDTO;
import br.com.badbit.algafoods.domain.model.Estado;
import br.com.badbit.algafoods.domain.repository.EstadoRepository;
import br.com.badbit.algafoods.domain.service.CadastroEstadoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private EstadoRepository estadoRepository;
    private CadastroEstadoService cadastroEstadoService;
    private EstadoDTOAssembler estadoDTOAssembler;
    private EstadoInputDisassembler estadoInputDisassembler;

    public EstadoController(EstadoRepository estadoRepository, CadastroEstadoService cadastroEstadoService, EstadoDTOAssembler estadoDTOAssembler, EstadoInputDisassembler estadoInputDisassembler) {
        this.estadoRepository = estadoRepository;
        this.cadastroEstadoService = cadastroEstadoService;
        this.estadoDTOAssembler = estadoDTOAssembler;
        this.estadoInputDisassembler = estadoInputDisassembler;
    }

    @GetMapping
    public CollectionModel<EstadoOutDTO> listar() {
        List<Estado> estados = estadoRepository.findAll();
        return estadoDTOAssembler.toCollectionModel(estados);
    }

    @GetMapping("/{estadoId}")
    public EstadoOutDTO buscar(@PathVariable Long estadoId) {
        Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);
        return estadoDTOAssembler.toModel(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoOutDTO salvar(@RequestBody @Valid EstadoInDTO estadoInDTO) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInDTO);
        estado = cadastroEstadoService.salvar(estado);
        return estadoDTOAssembler.toModel(estado);
    }

    @PutMapping("/{estadoId}")
    public EstadoOutDTO atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInDTO estadoInDTO) {
        Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);

        estadoInputDisassembler.copyToDomainObject(estadoInDTO, estadoAtual);
        estadoAtual = cadastroEstadoService.salvar(estadoAtual);

        return estadoDTOAssembler.toModel(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long estadoId) {
        cadastroEstadoService.excluir(estadoId);
    }

}
