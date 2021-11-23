package pl.training.shop.orders.products;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.training.shop.commons.Page;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.orders.Product;
import pl.training.shop.orders.ProductsProvider;
import pl.training.shop.orders.ServiceUnavailableException;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;

//@Primary
@RestController
@Log
@RequiredArgsConstructor(access = PACKAGE)
class RestTemplateProductsProviderAdapter implements ProductsProvider {

    private final ProductsRestMapper mapper;
    private final RestTemplate restTemplate;
    @Value("${api.products}")
    @Setter
    private String productsEndpoint;

    @Override
    public ResultPage<Product> getProducts(Page page) {
        try {
            var productsDto = restTemplate.getForObject(productsEndpoint, ProductDto[].class);
            var totalEntries = productsDto.length;
            var resultPage = new ResultPage<>(List.of(productsDto), page, totalEntries);
            return mapper.toModel(resultPage);
        } catch (RestClientException exception) {
            throw new ServiceUnavailableException(exception);
        }
    }

    @Override
    public Product getProduct(Long id) {
        try {
            var productDto = restTemplate.getForObject(productsEndpoint + "/" + id, ProductDto.class);
            return mapper.toModel(productDto);
        } catch (RestClientException exception) {
            throw new ServiceUnavailableException(exception);
        }
    }

}
