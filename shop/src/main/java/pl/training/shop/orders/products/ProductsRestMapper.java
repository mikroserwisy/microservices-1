package pl.training.shop.orders.products;

import org.mapstruct.Mapper;
import pl.training.shop.commons.FastMoneyMapper;
import pl.training.shop.commons.ResultPage;
import pl.training.shop.orders.Product;

import static java.util.stream.Collectors.toList;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
interface ProductsRestMapper {

    default ResultPage<Product> toModel(ResultPage<ProductDto> resultPage) {
        var products = resultPage.getEntries().stream()
                .map(this::toModel)
                .collect(toList());
        return new ResultPage<>(products, resultPage.getPage(), resultPage.getTotalEntries());
    }

    Product toModel(ProductDto productDto);

}
