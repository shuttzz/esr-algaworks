package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.exception.PermissaoNaoEncontradaException;
import br.com.badbit.algafoods.domain.model.Permissao;
import br.com.badbit.algafoods.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    public static final String MSG_PERMISSAO_EM_USO = "A permissão de id %d não pode ser removida, pois está em uso.";

    private PermissaoRepository permissaoRepository;

    public CadastroPermissaoService(PermissaoRepository permissaoRepository) {
        this.permissaoRepository = permissaoRepository;
    }

//    @Transactional
//    public Permissao salvar(Permissao permissao) {
//        return permissaoRepository.save(permissao);
//    }

//    @Transactional
//    public void excluir(Long permissaoId) {
//        try {
//            permissaoRepository.deleteById(permissaoId);
//            // Força o JPA a executar as operações no banco de dados, assim quando houver a exceção do tipo
//            // DataIntegrityViolationException o nosso catch vai conseguir capturar a mesma
//            permissaoRepository.flush();
//        } catch (EmptyResultDataAccessException e) {
//            throw new PermissaoNaoEncontradaException(permissaoId);
//        } catch (DataIntegrityViolationException e) {
//            throw new EntidadeEmUsoException(String.format(MSG_PERMISSAO_EM_USO, permissaoId));
//        }
//    }

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId).orElseThrow(
                () -> new PermissaoNaoEncontradaException(permissaoId));
    }
}
