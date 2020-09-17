package br.com.badbit.algafoods.api.model.input;

import br.com.badbit.algafoods.core.validation.FileContentType;
import br.com.badbit.algafoods.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoProdutoInDTO {

    @NotNull
    @FileSize(max = "1MB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile arquivo;
    @NotBlank
    private String descricao;

}
