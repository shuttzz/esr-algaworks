package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.assembler.FormaPagamentoDTOAssembler;
import br.com.badbit.algafoods.api.assembler.FormaPagamentoInputDisassembler;
import br.com.badbit.algafoods.api.model.input.FormaPagamentoInDTO;
import br.com.badbit.algafoods.api.model.output.FormaPagamentoOutDTO;
import br.com.badbit.algafoods.domain.model.FormaPagamento;
import br.com.badbit.algafoods.domain.repository.FormaPagamentoRepository;
import br.com.badbit.algafoods.domain.service.CadastroFormaPagamentoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
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
        public ResponseEntity<CollectionModel<FormaPagamentoOutDTO>> listar(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest()); // Desabilitar o ShallowEtag para essa requisição

        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        // Aqui verifica se vai precisar continuar o processamento
        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAll();
        CollectionModel<FormaPagamentoOutDTO> formaPagamentoOutDTOS = formaPagamentoDTOAssembler.toCollectionModel(formasPagamento);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(formaPagamentoOutDTOS);
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoOutDTO buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
        return formaPagamentoDTOAssembler.toModel(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoOutDTO salvar(@RequestBody @Valid FormaPagamentoInDTO formaPagamentoInDTO) {
        FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInDTO);
        formaPagamento = cadastroFormaPagamentoService.salvar(formaPagamento);
        return formaPagamentoDTOAssembler.toModel(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoOutDTO atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInDTO formaPagamentoInDTO) {
        FormaPagamento formaPagamentoAtual = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInDTO, formaPagamentoAtual);
        formaPagamentoAtual = cadastroFormaPagamentoService.salvar(formaPagamentoAtual);

        return formaPagamentoDTOAssembler.toModel(formaPagamentoAtual);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamentoService.excluir(formaPagamentoId);
    }

}
