package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.FotoProdutoDTOAssembler;
import br.com.badbit.algafoods.api.model.input.FotoProdutoInDTO;
import br.com.badbit.algafoods.api.model.output.FotoProdutoOutDTO;
import br.com.badbit.algafoods.domain.model.FotoProduto;
import br.com.badbit.algafoods.domain.model.Produto;
import br.com.badbit.algafoods.domain.service.CadastroProdutoService;
import br.com.badbit.algafoods.domain.service.CatalogoFotoProdutoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    private CadastroProdutoService cadastroProdutoService;
    private CatalogoFotoProdutoService catalogoFotoProdutoService;
    private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;

    public RestauranteProdutoFotoController(CadastroProdutoService cadastroProdutoService,
                                            CatalogoFotoProdutoService catalogoFotoProdutoService,
                                            FotoProdutoDTOAssembler fotoProdutoDTOAssembler) {
        this.cadastroProdutoService = cadastroProdutoService;
        this.catalogoFotoProdutoService = catalogoFotoProdutoService;
        this.fotoProdutoDTOAssembler = fotoProdutoDTOAssembler;
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoOutDTO atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoInDTO fotoProdutoInDTO) throws IOException {
        Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
        MultipartFile arquivo = fotoProdutoInDTO.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInDTO.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto, arquivo.getInputStream());

        return fotoProdutoDTOAssembler.toDTO(fotoSalva);
    }

}
