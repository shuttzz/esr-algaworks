package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.exception.EntidadeEmUsoException;
import br.com.badbit.algafoods.domain.exception.NegocioException;
import br.com.badbit.algafoods.domain.exception.UsuarioNaoEncontradoException;
import br.com.badbit.algafoods.domain.model.Grupo;
import br.com.badbit.algafoods.domain.model.Usuario;
import br.com.badbit.algafoods.domain.repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CadastroUsuarioService {

    public static final String MSG_USUARIO_EM_USO = "O usuário de id %d não pode ser removido, pois está em uso.";

    private UsuarioRepository usuarioRepository;
    private CadastroGrupoService cadastroGrupoService;
    private PasswordEncoder passwordEncoder;

    public CadastroUsuarioService(UsuarioRepository usuarioRepository, CadastroGrupoService cadastroGrupoService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.cadastroGrupoService = cadastroGrupoService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        // Retirando o usuário do contexto de persistência para que o JPA não faça o update antes de fazer o select por e-mail
        usuarioRepository.detach(usuario);

        if (usuario.isNovo()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);

        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(novaSenha);
    }

    @Transactional
    public void excluir(Long usuarioId) {
        try {
            usuarioRepository.deleteById(usuarioId);
            // Força o JPA a executar as operações no banco de dados, assim quando houver a exceção do tipo
            // DataIntegrityViolationException o nosso catch vai conseguir capturar a mesma
            usuarioRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(usuarioId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, usuarioId));
        }
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new UsuarioNaoEncontradoException(usuarioId));
    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        usuario.removerGrupo(grupo);
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        usuario.adicionarGrupo(grupo);
    }

}
