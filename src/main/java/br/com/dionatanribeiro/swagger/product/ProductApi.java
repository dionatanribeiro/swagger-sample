package br.com.dionatanribeiro.swagger.product;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Produto", description = "Endpoints de produtos")
public interface ProductApi {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto encontrado", response = ProductDto.class),
            @ApiResponse(code = 404, message = "Nenhum produto disponível")
    })
    @ApiImplicitParams(
            @ApiImplicitParam(name="X-PRODUCT-GET", value="Header específico", required = false, paramType = "header")
    )
    @ApiOperation(
            value = "Buscar todos os produtos",
            notes = "Retorna o catálogo disponível de produtos",
            response = ProductDto.class
            , responseContainer = "List"
    )
    ResponseEntity<List<ProductDto>> findAll();

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto criado com sucesso", response = ProductDto.class),
    })
    @ApiImplicitParams(
            @ApiImplicitParam(name="X-API-MARKETPLACE", value="Header sobreescrito", required = true, paramType = "header")
    )
    @ApiOperation(value = "Cadastrar produto", response = ProductDto.class, code = 201, notes = "teste")
    ResponseEntity<ProductDto> postProduct(@RequestBody ProductDto productDto);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto encontrado", response = ProductDto.class),
            @ApiResponse(code = 404, message = "Id de produto não encontrado")
    })
    @ApiOperation(value = "Buscar produto por id", notes = "Deve-se informar um Id válido")
    ProductDto findById(@ApiParam(value = "Id de produto que deseja ser buscado", required=true) Long idProduct);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto atualizado com sucesso", response = ProductDto.class),
            @ApiResponse(code = 404, message = "Não foi possível atualizar o produto, produto não encontrado")
    })
    @ApiOperation(value = "Atualizar produto por Id", notes = "Deve-se informar um Id válido")
    ProductDto updateProduct(@ApiParam(value = "Id de produto que deseja ser atualizado", required=true) @PathVariable("id") Long idProduct, @ApiParam(value = "Valores de Produto para ser atualizado", required=true) @RequestBody ProductDto productDto);

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Produto excluído com sucesso"),
            @ApiResponse(code = 404, message = "Não foi possível excluir o produto, produto não encontrado")
    })
    @ApiOperation(value = "Excluir produto por Id", notes = "Deve-se informar um Id válido       Produto não ficará mais disponível")
    void deleteProduct();

}
