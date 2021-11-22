package pl.training.shop.orders.products;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "productsClient", url = "${api.products}")
interface ProductsApi {

    @GetMapping
    List<ProductDto> getProducts(@RequestParam("_start") int pageIndex, @RequestParam("_limit")int pageSize);

    @GetMapping("count")
    int getProductsCount();

    @GetMapping("{id}")
    ProductDto getById(@PathVariable Long id);

}
