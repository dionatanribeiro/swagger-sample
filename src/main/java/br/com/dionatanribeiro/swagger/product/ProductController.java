package br.com.dionatanribeiro.swagger.product;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController implements ProductApi {

    @Override
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> productList = Lists.newArrayList(
                ProductDto.builder()
                        .name("arroz")
                        .price(15.6)
                        .quantity(2)
                        .stock(98)
                        .build(),
                ProductDto.builder()
                        .name("feijão")
                        .price(19.2)
                        .quantity(4)
                        .stock(56)
                        .build(),
                ProductDto.builder()
                        .name("massa")
                        .price(5.60)
                        .quantity(5)
                        .stock(23)
                        .build()
        );
        return ResponseEntity.ok(productList);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ProductDto> postProduct(@ApiParam(value = "Valores de Produto para ser inserido", required=true) @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @Override
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable("id") Long idProduct) {
        return ProductDto.builder()
                .name("arroz")
                .price(15.6)
                .quantity(2)
                .stock(98)
                .build();
    }

    @Override
    @PutMapping("/{id}")
    public ProductDto updateProduct(@ApiParam(value = "Id de produto que deseja ser atualizado", required=true) @PathVariable("id") Long idProduct, @ApiParam(value = "Valores de Produto para ser atualizado", required=true) @RequestBody ProductDto productDto) {
        return productDto;
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct() {
    }

}
