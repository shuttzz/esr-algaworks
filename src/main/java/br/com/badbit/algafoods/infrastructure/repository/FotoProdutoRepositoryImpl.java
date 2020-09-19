package br.com.badbit.algafoods.infrastructure.repository;

import br.com.badbit.algafoods.domain.model.FotoProduto;
import br.com.badbit.algafoods.domain.repository.FotoProdutoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class FotoProdutoRepositoryImpl implements FotoProdutoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public FotoProduto save(FotoProduto foto) {
        return manager.merge(foto);
    }

    @Transactional
    @Override
    public void delete(FotoProduto foto) {
        manager.remove(foto);
    }

}
