package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.output.ProdutoOutDTO;
import br.com.badbit.algafoods.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoDTOAssembler {

    private ModelMapper modelMapper;

    public ProdutoDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProdutoOutDTO toDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoOutDTO.class);
    }

    public List<ProdutoOutDTO> toCollectionDTO(List<Produto> grupos) {
        return grupos.stream()
                .map(estado -> toDTO(estado))
                .collect(Collectors.toList());
    }

}
