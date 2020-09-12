package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.exception.ProdutoNaoEncontradoException;
import br.com.badbit.algafoods.domain.model.Produto;
import br.com.badbit.algafoods.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroProdutoService {

    private ProdutoRepository produtoRepository;

    public CadastroProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }
}
