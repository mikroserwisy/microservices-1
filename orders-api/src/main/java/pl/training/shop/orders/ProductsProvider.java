package pl.training.shop.orders;

import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;

public interface ProductsProvider {

    ResultPage<Product> getProducts(Page page);

    Product getProduct(Long id);

}
