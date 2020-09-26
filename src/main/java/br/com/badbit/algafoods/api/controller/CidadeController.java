package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.ResourceUriHelper;
import br.com.badbit.algafoods.api.assembler.CidadeDTOAssembler;
import br.com.badbit.algafoods.api.assembler.CidadeInputDisassembler;
import br.com.badbit.algafoods.api.model.input.CidadeInDTO;
import br.com.badbit.algafoods.api.model.output.CidadeOutDTO;
import br.com.badbit.algafoods.api.openapi.controller.CidadeControllerOpenApi;
import br.com.badbit.algafoods.domain.exception.EstadoNaoEncontradoException;
import br.com.badbit.algafoods.domain.exception.NegocioException;
import br.com.badbit.algafoods.domain.model.Cidade;
import br.com.badbit.algafoods.domain.repository.CidadeRepository;
import br.com.badbit.algafoods.domain.service.CadastroCidadeService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    private CadastroCidadeService cadastroCidadeService;
    private CidadeRepository cidadeRepository;
    private CidadeDTOAssembler cidadeDTOAssembler;
    private CidadeInputDisassembler cidadeInputDisassembler;

    public CidadeController(CadastroCidadeService cadastroCidadeService, CidadeRepository cidadeRepository, CidadeDTOAssembler cidadeDTOAssembler, CidadeInputDisassembler cidadeInputDisassembler) {
        this.cadastroCidadeService = cadastroCidadeService;
        this.cidadeRepository = cidadeRepository;
        this.cidadeDTOAssembler = cidadeDTOAssembler;
        this.cidadeInputDisassembler = cidadeInputDisassembler;
    }

    @Override
    @GetMapping
    public CollectionModel<CidadeOutDTO> listar() {
        List<Cidade> cidades = cidadeRepository.findAll();
        return cidadeDTOAssembler.toCollectionModel(cidades);
    }

    @Override
    @GetMapping("/{cidadeId}")
    public CidadeOutDTO buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);
        return cidadeDTOAssembler.toModel(cidade);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeOutDTO salvar(@RequestBody @Valid CidadeInDTO cidadeInDTO) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInDTO);
            cidade = cadastroCidadeService.salvar(cidade);

            CidadeOutDTO cidadeOutDTO =  cidadeDTOAssembler.toModel(cidade);

            // Adiconar o Location ao header da response
            ResourceUriHelper.addUriInResponseHeader(cidadeOutDTO.getId());

            return cidadeOutDTO;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    @PutMapping("/{cidadeId}")
    public CidadeOutDTO atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInDTO cidadeInDTO) {
        try {
            Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);
            cidadeInputDisassembler.copyToDomainObject(cidadeInDTO, cidadeAtual);
            cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);
            return cidadeDTOAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @Override
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cidadeId) {
        cadastroCidadeService.excluir(cidadeId);
    }

}
