package br.com.dionatanribeiro.swagger.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
@ApiModel("ApiError")
public class ExceptionResponse {

    @ApiModelProperty("Data do Erro")
    private Date timestamp;

    @ApiModelProperty("Mensagem de Erro")
    private String message;

    @ApiModelProperty("Detalhe do Erro")
    private String detail;

}
