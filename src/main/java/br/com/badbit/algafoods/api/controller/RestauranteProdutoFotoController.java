package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.model.input.FotoProdutoInDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoInDTO fotoProdutoInDTO) {
//        var nomeArquivo = UUID.randomUUID().toString() + "-" + fotoProdutoInDTO.getArquivo().getOriginalFilename();
//        var arquivoFoto = Path.of("C:\\teste-fotos", nomeArquivo);
//        try {
//            fotoProdutoInDTO.getArquivo().transferTo(arquivoFoto);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }

}
