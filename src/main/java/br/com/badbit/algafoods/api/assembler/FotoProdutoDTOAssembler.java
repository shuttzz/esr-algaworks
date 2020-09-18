package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.output.FotoProdutoOutDTO;
import br.com.badbit.algafoods.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FotoProdutoDTOAssembler {

    private ModelMapper modelMapper;

    public FotoProdutoDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FotoProdutoOutDTO toDTO(FotoProduto fotoProduto) {
        return modelMapper.map(fotoProduto, FotoProdutoOutDTO.class);
    }

    public List<FotoProdutoOutDTO> toCollectionDTO(List<FotoProduto> fotoProdutos) {
        return fotoProdutos.stream()
                .map(fotoProduto -> toDTO(fotoProduto))
                .collect(Collectors.toList());
    }

}
