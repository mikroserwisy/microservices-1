package pl.training.shop.products;

import java.util.List;

interface ProductsService {

    List<Product> getProducts();

    Product getProduct(Long id);

    void refresh();

}
