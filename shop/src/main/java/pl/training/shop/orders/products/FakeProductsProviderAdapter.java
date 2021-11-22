package pl.training.shop.orders.products;

import org.springframework.stereotype.Component;
import pl.training.shop.commons.LocalMoney;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.orders.Product;
import pl.training.shop.orders.ProductsProvider;

import java.util.Collections;

@Component
class FakeProductsProviderAdapter implements ProductsProvider {

    @Override
    public ResultPage<Product> getProducts(Page page) {
        return new ResultPage<>(Collections.emptyList(), page, 0);
    }

    @Override
    public Product getProduct(Long id) {
        return new Product(1L, "Product", LocalMoney.of(10));
    }

}
