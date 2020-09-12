package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.FormaPagamentoDTOAssembler;
import br.com.badbit.algafoods.api.assembler.FormaPagamentoInputDisassembler;
import br.com.badbit.algafoods.api.model.input.FormaPagamentoInDTO;
import br.com.badbit.algafoods.api.model.output.FormaPagamentoOutDTO;
import br.com.badbit.algafoods.domain.model.FormaPagamento;
import br.com.badbit.algafoods.domain.repository.FormaPagamentoRepository;
import br.com.badbit.algafoods.domain.service.CadastroFormaPagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    private FormaPagamentoRepository formaPagamentoRepository;
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    public FormaPagamentoController(FormaPagamentoRepository formaPagamentoRepository, CadastroFormaPagamentoService cadastroFormaPagamentoService,
                                    FormaPagamentoDTOAssembler formaPagamentoDTOAssembler, FormaPagamentoInputDisassembler formaPagamentoInputDisassembler) {
        this.formaPagamentoRepository = formaPagamentoRepository;
        this.cadastroFormaPagamentoService = cadastroFormaPagamentoService;
        this.formaPagamentoDTOAssembler = formaPagamentoDTOAssembler;
        this.formaPagamentoInputDisassembler = formaPagamentoInputDisassembler;
    }

    @GetMapping
    public List<FormaPagamentoOutDTO> listar() {
        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAll();
        return formaPagamentoDTOAssembler.toCollectionDTO(formasPagamento);
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoOutDTO buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
        return formaPagamentoDTOAssembler.toDTO(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoOutDTO salvar(@RequestBody @Valid FormaPagamentoInDTO formaPagamentoInDTO) {
        FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInDTO);
        formaPagamento = cadastroFormaPagamentoService.salvar(formaPagamento);
        return formaPagamentoDTOAssembler.toDTO(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoOutDTO atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInDTO formaPagamentoInDTO) {
        FormaPagamento formaPagamentoAtual = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInDTO, formaPagamentoAtual);
        formaPagamentoAtual = cadastroFormaPagamentoService.salvar(formaPagamentoAtual);

        return formaPagamentoDTOAssembler.toDTO(formaPagamentoAtual);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamentoService.excluir(formaPagamentoId);
    }

}
