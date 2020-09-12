package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.UsuarioDTOAssembler;
import br.com.badbit.algafoods.api.assembler.UsuarioInputDisassembler;
import br.com.badbit.algafoods.api.model.input.SenhaInDTO;
import br.com.badbit.algafoods.api.model.input.UsuarioComSenhaInDTO;
import br.com.badbit.algafoods.api.model.input.UsuarioInDTO;
import br.com.badbit.algafoods.api.model.output.UsuarioOutDTO;
import br.com.badbit.algafoods.domain.model.Usuario;
import br.com.badbit.algafoods.domain.repository.UsuarioRepository;
import br.com.badbit.algafoods.domain.service.CadastroUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;
    private CadastroUsuarioService cadastroUsuarioService;
    private UsuarioDTOAssembler usuarioDTOAssembler;
    private UsuarioInputDisassembler usuarioInputDisassembler;

    public UsuarioController(UsuarioRepository usuarioRepository, CadastroUsuarioService cadastroUsuarioService, UsuarioDTOAssembler usuarioDTOAssembler, UsuarioInputDisassembler usuarioInputDisassembler) {
        this.usuarioRepository = usuarioRepository;
        this.cadastroUsuarioService = cadastroUsuarioService;
        this.usuarioDTOAssembler = usuarioDTOAssembler;
        this.usuarioInputDisassembler = usuarioInputDisassembler;
    }

    @GetMapping
    public List<UsuarioOutDTO> listar() {
        List<Usuario> grupos = usuarioRepository.findAll();
        return usuarioDTOAssembler.toCollectionDTO(grupos);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioOutDTO buscar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        return usuarioDTOAssembler.toDTO(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioOutDTO salvar(@RequestBody @Valid UsuarioComSenhaInDTO usuarioInDTO) {
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInDTO);
        usuario = cadastroUsuarioService.salvar(usuario);
        return usuarioDTOAssembler.toDTO(usuario);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioOutDTO atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInDTO usuarioInDTO) {
        Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        usuarioInputDisassembler.copyToDomainObject(usuarioInDTO, usuarioAtual);
        usuarioAtual = cadastroUsuarioService.salvar(usuarioAtual);

        return usuarioDTOAssembler.toDTO(usuarioAtual);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInDTO senhaInDTO) {
        cadastroUsuarioService.alterarSenha(usuarioId, senhaInDTO.getSenhaAtual(), senhaInDTO.getNovaSenha());
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long usuarioId) {
        cadastroUsuarioService.excluir(usuarioId);
    }

}
