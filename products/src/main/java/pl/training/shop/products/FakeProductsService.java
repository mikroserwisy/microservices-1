package pl.training.shop.products;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class FakeProductsService implements ProductsService {

    private static final List<Product> PRODUCTS = List.of(
            new Product(1L, "Spring in action", 200L),
            new Product(2L, "Angular in action", 100L)
        );

    @Override
    public List<Product> getProducts() {
        return PRODUCTS;
    }

    @Override
    public Product getProduct(Long id) {
        return PRODUCTS.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
