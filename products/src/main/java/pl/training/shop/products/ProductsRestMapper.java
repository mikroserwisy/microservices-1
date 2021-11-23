package pl.training.shop.products;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
interface ProductsRestMapper {

    ProductDto toDto(Product product);

    @IterableMapping(elementTargetType = ProductDto.class)
    List<ProductDto> toDto(List<Product> products);

}
