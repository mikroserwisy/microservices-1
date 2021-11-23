package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;

@RestController
@RequiredArgsConstructor(access = PACKAGE)
class ProductsController {

    private final ProductsRestMapper mapper;
    private final ProductsService productsService;

    @GetMapping("products")
    ResponseEntity<List<ProductDto>> getProducts() {
        var products = productsService.getProducts();
        return ResponseEntity.ok(mapper.toDto(products));
    }

}
