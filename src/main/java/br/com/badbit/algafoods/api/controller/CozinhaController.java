package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.CozinhaDTOAssembler;
import br.com.badbit.algafoods.api.assembler.CozinhaInputDisassembler;
import br.com.badbit.algafoods.api.model.input.CozinhaInDTO;
import br.com.badbit.algafoods.api.model.output.CozinhaOutDTO;
import br.com.badbit.algafoods.domain.model.Cozinha;
import br.com.badbit.algafoods.domain.repository.CozinhaRepository;
import br.com.badbit.algafoods.domain.service.CadastroCozinhaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private CozinhaRepository cozinhaRepository;
    private CadastroCozinhaService cadastroCozinhaService;
    private CozinhaDTOAssembler cozinhaDTOAssembler;
    private CozinhaInputDisassembler cozinhaInputDisassembler;
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    public CozinhaController(CozinhaRepository cozinhaRepository, CadastroCozinhaService cadastroCozinhaService,
                             CozinhaDTOAssembler cozinhaDTOAssembler, CozinhaInputDisassembler cozinhaInputDisassembler,
                             PagedResourcesAssembler<Cozinha> pagedResourcesAssembler) {
        this.cozinhaRepository = cozinhaRepository;
        this.cadastroCozinhaService = cadastroCozinhaService;
        this.cozinhaDTOAssembler = cozinhaDTOAssembler;
        this.cozinhaInputDisassembler = cozinhaInputDisassembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping
//    public Page<CozinhaOutDTO> listar(@PageableDefault(size = 2) Pageable pageable) {
    public PagedModel<CozinhaOutDTO> listar(Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

//        List<CozinhaOutDTO> cozinhasOutDTO = cozinhaDTOAssembler.toCollectionDTO(cozinhasPage.getContent());
//        Page<CozinhaOutDTO> cozinhasOutDTOPage = new PageImpl<>(cozinhasOutDTO, pageable, cozinhasPage.getTotalElements());
        PagedModel<CozinhaOutDTO> cozinhasOutDTOPage = pagedResourcesAssembler.toModel(cozinhasPage, cozinhaDTOAssembler);

        return cozinhasOutDTOPage;
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaOutDTO buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
        return cozinhaDTOAssembler.toModel(cozinha);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaOutDTO adicionar(@RequestBody @Valid CozinhaInDTO cozinhaInDTO) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInDTO);
        cozinha = cadastroCozinhaService.salvar(cozinha);
        return cozinhaDTOAssembler.toModel(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaOutDTO atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInDTO cozinhaInDTO) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInDTO, cozinhaAtual);
        cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);
        return cozinhaDTOAssembler.toModel(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.excluir(cozinhaId);
    }

}
