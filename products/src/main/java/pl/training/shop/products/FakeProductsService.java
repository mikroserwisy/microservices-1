package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;

@Service
@RequiredArgsConstructor(access = PACKAGE)
class FakeProductsService implements ProductsService {

    private final static String CHANNEL_NAME = "products-out-0";

    private final StreamBridge streamBridge;

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
        streamBridge.send(CHANNEL_NAME, "Updating products...");
        return PRODUCTS.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
