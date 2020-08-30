package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.domain.model.Estado;
import br.com.badbit.algafoods.domain.repository.EstadoRepository;
import br.com.badbit.algafoods.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private EstadoRepository estadoRepository;
    private CadastroEstadoService cadastroEstadoService;

    public EstadoController(EstadoRepository estadoRepository, CadastroEstadoService cadastroEstadoService) {
        this.estadoRepository = estadoRepository;
        this.cadastroEstadoService = cadastroEstadoService;
    }

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return cadastroEstadoService.buscarOuFalhar(estadoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado salvar(@RequestBody Estado estado) {
        return cadastroEstadoService.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);
        BeanUtils.copyProperties(estado, estadoAtual, "id", "createdAt");
        return cadastroEstadoService.salvar(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long estadoId) {
        cadastroEstadoService.excluir(estadoId);
    }

}
