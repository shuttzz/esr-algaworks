package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.exception.FotoProdutoNaoEncontradaException;
import br.com.badbit.algafoods.domain.model.FotoProduto;
import br.com.badbit.algafoods.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    private ProdutoRepository produtoRepository;
    private FotoStorageService fotoStorageService;

    public CatalogoFotoProdutoService(ProdutoRepository produtoRepository, FotoStorageService fotoStorageService) {
        this.produtoRepository = produtoRepository;
        this.fotoStorageService = fotoStorageService;
    }

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String novoNomeArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;

//        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

//        if (fotoExistente.isPresent()) {
//            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
////            produtoRepository.delete(fotoExistente.get());
//        }

        foto.setNomeArquivo(novoNomeArquivo);
        foto = produtoRepository.save(foto);
//        produtoRepository.flush();

        FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(dadosArquivo)
                .build();

//        fotoStorageService.substituir(nomeArquivoExistente, novaFoto);

        return foto;
    }

    @Transactional
    public void excluir(Long restauranteId, Long produtoId) {
        FotoProduto foto = buscarOuFalhar(restauranteId, produtoId);

//        produtoRepository.delete(foto);
        produtoRepository.flush();

        fotoStorageService.remover(foto.getNomeArquivo());
    }

    public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findFotoById(restauranteId, produtoId)
                .orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
    }

}
