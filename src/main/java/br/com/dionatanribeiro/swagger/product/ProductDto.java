package br.com.dionatanribeiro.swagger.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("Produto")
public class ProductDto {

    @ApiModelProperty(value = "Nome do Produto", required = true)
    private String name;

    @ApiModelProperty(value = "Unidades do Produto", required = true)
    private Integer quantity;

    @ApiModelProperty(value = "Preço do Produto", required = true)
    private Double price;

    @ApiModelProperty(value = "Estoque disponível do Produto", readOnly = true)
    private Integer stock;

}
