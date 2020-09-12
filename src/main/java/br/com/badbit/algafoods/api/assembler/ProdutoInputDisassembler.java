package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.input.ProdutoInDTO;
import br.com.badbit.algafoods.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler {

    private ModelMapper modelMapper;

    public ProdutoInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Produto toDomainObject(ProdutoInDTO produtoInDTO) {
        return modelMapper.map(produtoInDTO, Produto.class);
    }

    public void copyToDomainObject(ProdutoInDTO produtoInDTO, Produto produto) {
        modelMapper.map(produtoInDTO, produto);
    }

}
