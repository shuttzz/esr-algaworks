package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.PermissaoDTOAssembler;
import br.com.badbit.algafoods.api.model.output.PermissaoOutDTO;
import br.com.badbit.algafoods.domain.model.Permissao;
import br.com.badbit.algafoods.domain.repository.PermissaoRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController {

    private final PermissaoRepository permissaoRepository;
    private final PermissaoDTOAssembler permissaoDTOAssembler;

    public PermissaoController(PermissaoRepository permissaoRepository, PermissaoDTOAssembler permissaoDTOAssembler) {
        this.permissaoRepository = permissaoRepository;
        this.permissaoDTOAssembler = permissaoDTOAssembler;
    }

    @GetMapping
    public CollectionModel<PermissaoOutDTO> listar() {
        List<Permissao> todasPermissoes = permissaoRepository.findAll();

        return permissaoDTOAssembler.toCollectionModel(todasPermissoes);
    }

}
