package pl.training.shop.orders.products;

import feign.FeignException.FeignClientException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.orders.Product;
import pl.training.shop.orders.ProductsProvider;
import pl.training.shop.orders.ServiceUnavailableException;

import static lombok.AccessLevel.PACKAGE;

//@Primary
@Component
@Log
@RequiredArgsConstructor(access = PACKAGE)
class FeignProductsProviderAdapter implements ProductsProvider {

    private final ProductsRestMapper mapper;
    private final ProductsApi productsApi;
    @Value("${api.products}")
    @Setter
    private String productsEndpoint;

    @Override
    public ResultPage<Product> getProducts(Page page) {
        try {
            var productsDto = productsApi.getProducts(page.getIndex() * page.getSize(), page.getSize());
            var totalEntries = productsApi.getProductsCount();
            var resultPage = new ResultPage<>(productsDto, page, totalEntries);
            return mapper.toModel(resultPage);
        } catch (FeignClientException exception) {
            throw new ServiceUnavailableException(exception);
        }
    }

    @Override
    public Product getProduct(Long id) {
        try {
            var productDto = productsApi.getById(id);
            return mapper.toModel(productDto);
        } catch (FeignClientException exception) {
            throw new ServiceUnavailableException(exception);
        }
    }

}
