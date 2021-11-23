package pl.training.shop.orders.products;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "productsClient", url = "${api.products}")
@FeignClient("PRODUCTS-SERVICE")
interface ProductsApi {

    @GetMapping("products")
    List<ProductDto> getProducts();

    @GetMapping("products/{id}")
    ProductDto getById(@PathVariable Long id);

}
