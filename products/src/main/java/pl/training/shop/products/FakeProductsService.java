package pl.training.shop.products;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class FakeProductsService implements ProductsService {

    @Override
    public List<Product> getProducts() {
        return List.of(
                new Product(1L, "Spring in action", 200L),
                new Product(2L, "Angular in action", 100L)
        );
    }

}
