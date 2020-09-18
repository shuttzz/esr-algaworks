package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);
    void delete(FotoProduto foto);

}
