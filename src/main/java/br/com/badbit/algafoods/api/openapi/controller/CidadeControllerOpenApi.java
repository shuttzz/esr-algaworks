package br.com.badbit.algafoods.api.openapi.controller;

import br.com.badbit.algafoods.api.model.input.CidadeInDTO;
import br.com.badbit.algafoods.api.model.output.CidadeOutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation("Listagem de cidades")
    CollectionModel<CidadeOutDTO> listar();

    CidadeOutDTO buscar(@ApiParam(value = "ID de uma cidade") Long cidadeId);

    @ApiOperation("Salva uma nova cidade")
    CidadeOutDTO salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade") CidadeInDTO cidadeInDTO);

    @ApiOperation("Atualiza uma cidade")
    CidadeOutDTO atualizar(Long cidadeId, CidadeInDTO cidadeInDTO);

    @ApiOperation("Deleta uma cidade")
    void deletar(Long cidadeId);

}
