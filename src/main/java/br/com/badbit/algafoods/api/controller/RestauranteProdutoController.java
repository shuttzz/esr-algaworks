package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.assembler.ProdutoDTOAssembler;
import br.com.badbit.algafoods.api.assembler.ProdutoInputDisassembler;
import br.com.badbit.algafoods.api.model.input.ProdutoInDTO;
import br.com.badbit.algafoods.api.model.output.ProdutoOutDTO;
import br.com.badbit.algafoods.domain.model.Produto;
import br.com.badbit.algafoods.domain.model.Restaurante;
import br.com.badbit.algafoods.domain.repository.ProdutoRepository;
import br.com.badbit.algafoods.domain.service.CadastroProdutoService;
import br.com.badbit.algafoods.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    private ProdutoRepository produtoRepository;
    private CadastroProdutoService cadastroProdutoService;
    private CadastroRestauranteService cadastroRestauranteService;
    private ProdutoDTOAssembler produtoDTOAssembler;
    private ProdutoInputDisassembler produtoInputDisassembler;
    private final AlgaLinks algaLinks;

    public RestauranteProdutoController(ProdutoRepository produtoRepository, CadastroProdutoService cadastroProdutoService,
                                        CadastroRestauranteService cadastroRestauranteService, ProdutoDTOAssembler produtoDTOAssembler,
                                        ProdutoInputDisassembler produtoInputDisassembler, AlgaLinks algaLinks) {
        this.produtoRepository = produtoRepository;
        this.cadastroProdutoService = cadastroProdutoService;
        this.cadastroRestauranteService = cadastroRestauranteService;
        this.produtoDTOAssembler = produtoDTOAssembler;
        this.produtoInputDisassembler = produtoInputDisassembler;
        this.algaLinks = algaLinks;
    }

    @GetMapping
    public CollectionModel<ProdutoOutDTO> listar(@PathVariable Long restauranteId, @RequestParam(required = false) Boolean incluirInativos) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        System.out.println(incluirInativos);

        List<Produto> todosProdutos = null;

        if (incluirInativos) {
            todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
        } else {
            todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
        }

        return produtoDTOAssembler.toCollectionModel(todosProdutos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoOutDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);

        return produtoDTOAssembler.toModel(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoOutDTO adicionar(@PathVariable Long restauranteId,
                                  @RequestBody @Valid ProdutoInDTO produtoInDTO) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInDTO);
        produto.setRestaurante(restaurante);

        produto = cadastroProdutoService.salvar(produto);

        return produtoDTOAssembler.toModel(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoOutDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                  @RequestBody @Valid ProdutoInDTO produtoInDTO) {
        Produto produtoAtual = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);

        produtoInputDisassembler.copyToDomainObject(produtoInDTO, produtoAtual);

        produtoAtual = cadastroProdutoService.salvar(produtoAtual);

        return produtoDTOAssembler.toModel(produtoAtual);
    }

}
