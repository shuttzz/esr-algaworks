package br.com.badbit.algafoods.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400", position = 1)
    private Integer status;
    @ApiModelProperty(example = "http://localhost:8080/dados-inválidos", position = 2)
    private String type;
    @ApiModelProperty(example = "Dados inválidos", position = 3)
    private String title;
    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenhcimento correto e tente novamente", position = 4)
    private String detail;
    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenhcimento correto e tente novamente", position = 5)
    private String userMessage;

    @ApiModelProperty(example = "2020-09-24T04:16:02.70844Z", position = 6)
    private OffsetDateTime timestamp;
    @ApiModelProperty(value = "Objetos ou campos que geraram o erro", position = 7)
    private List<Object> objects;

    @ApiModel("ObjetoProblema")
    @Getter
    @Builder
    public static class Object {
        @ApiModelProperty(example = "Preço", position = 1)
        private String nome;
        @ApiModelProperty(example = "O preço é obrigatório", position = 2)
        private String userMessage;
    }

}
