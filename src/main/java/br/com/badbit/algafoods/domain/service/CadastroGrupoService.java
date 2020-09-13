package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.exception.EntidadeEmUsoException;
import br.com.badbit.algafoods.domain.exception.GrupoNaoEncontradoException;
import br.com.badbit.algafoods.domain.model.Grupo;
import br.com.badbit.algafoods.domain.model.Permissao;
import br.com.badbit.algafoods.domain.repository.GrupoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroGrupoService {

    public static final String MSG_GRUPO_EM_USO = "O grupo de id %d não pode ser removido, pois está em uso.";

    private GrupoRepository grupoRepository;
    private CadastroPermissaoService cadastroPermissaoService;

    public CadastroGrupoService(GrupoRepository grupoRepository, CadastroPermissaoService cadastroPermissaoService) {
        this.grupoRepository = grupoRepository;
        this.cadastroPermissaoService = cadastroPermissaoService;
    }

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long grupoId) {
        try {
            grupoRepository.deleteById(grupoId);
            // Força o JPA a executar as operações no banco de dados, assim quando houver a exceção do tipo
            // DataIntegrityViolationException o nosso catch vai conseguir capturar a mesma
            grupoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(grupoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, grupoId));
        }
    }

    public Grupo buscarOuFalhar(Long grupoId) {
        return grupoRepository.findById(grupoId).orElseThrow(
                () -> new GrupoNaoEncontradoException(grupoId));
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permisaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permisaoId);

        grupo.removerPermissao(permissao);
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permisaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permisaoId);

        grupo.adicionarPermissao(permissao);
    }

}
