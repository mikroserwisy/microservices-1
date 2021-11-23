package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;

@RequestMapping("products")
@RestController
@RequiredArgsConstructor(access = PACKAGE)
class ProductsController {

    private final ProductsRestMapper mapper;
    private final ProductsService productsService;

    @GetMapping
    ResponseEntity<List<ProductDto>> getProducts() {
        var products = productsService.getProducts();
        return ResponseEntity.ok(mapper.toDto(products));
    }

    @GetMapping("{id}")
    ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        var product = productsService.getProduct(id);
        return ResponseEntity.ok(mapper.toDto(product));
    }

}
